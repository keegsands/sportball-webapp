package com.bocano.softball.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocano.softball.dao.CampaignDAO;
import com.bocano.softball.dao.GameDAO;
import com.bocano.softball.dao.SeasonDAO;
import com.bocano.softball.model.Campaign;
import com.bocano.softball.model.Division;
import com.bocano.softball.model.Game;
import com.bocano.softball.model.Season;
import com.bocano.softball.model.Standing;
import com.bocano.softball.service.SeasonService;
import com.bocano.softball.util.BaseballUtil;

@Service
public class SeasonServiceImpl extends AbstractSimpleServiceImpl<Season, SeasonDAO> implements SeasonService {


	
	public void setSeasonDAO(final SeasonDAO seasonDAO) {
		super.setDAO(seasonDAO);
	}

	private GameDAO gameDAO;

	public void setGameDAO(final GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
	
	private CampaignDAO campaignDAO;
	
	public void setCampaignDAO(final CampaignDAO campaignDAO){
		this.campaignDAO = campaignDAO;
	}

	@Override
	@Transactional
	public List<Standing> getStandings(int seasonID) {
		final List<Standing> standings;
		final List<Campaign> campaigns = campaignDAO.findBySeason(seasonID);
		standings = buildStandingsFromCampaigns(campaigns);
		
		return standings;
	}
	
	private void processStandings(final List<Standing> standings){
		if(standings.size() != 0){
			Collections.sort(standings);
			BaseballUtil.fixStandingsTieBreaker(standings);
	
			final Double firstPlaceWins = standings.get(0).getWins() + (.5 * standings.get(0).getTies());
			final int firstPlaceLosses = standings.get(0).getLosses();
			for (final Standing standing : standings) {
				final Double currentWins = standing.getWins() + (.5 * standing.getTies());
				standing.setGamesBack(BaseballUtil.calculateGamesBack(firstPlaceWins.floatValue(), firstPlaceLosses, currentWins.floatValue(), standing.getLosses()));
			}
		}
	}
	
	private List<Standing> buildStandingsFromCampaigns(List<Campaign> campaigns){
		final List<Standing> standings = new ArrayList<Standing>();
		final Map<Division, List<Standing>> divisionMap = new HashMap<Division, List<Standing>>();
		for (final Campaign campaign : campaigns) {
			final List<Game> games = gameDAO.findByTeamAndSeason(campaign.getTeam().getId(), campaign.getSeason().getId());
			// If there are games for the team for this season then create a new Standing object
			if(null != games && !games.isEmpty()){
				final Standing standing = new Standing();
				standing.setCampaign(campaign);
				standing.setTeam(campaign.getTeam());
				standing.setGames(games);
				standing.initData();
				final List<Standing> divisionStandings;
				if(!divisionMap.containsKey(campaign.getDivision())){
					divisionStandings = new ArrayList<Standing>();
					divisionMap.put(campaign.getDivision(), divisionStandings);	
				}else{
					divisionStandings = divisionMap.get(campaign.getDivision());
				}
				divisionStandings.add(standing);
			}
		}
		if(!divisionMap.isEmpty()){
			final List<Division> divisions = new ArrayList<Division>();
			for (Division division : divisionMap.keySet()) {
			    divisions.add(division);
			}
			Collections.sort(divisions);
			for(final Division division : divisions){
				final List<Standing> divisionStandings = divisionMap.get(division);
				processStandings(divisionStandings);
				standings.addAll(divisionStandings);
			}
		}
		return standings;
	}
	

	@Override
	@Transactional
	public List<Season> listStandingSeasons() {
		return getDAO().listStandingSeasons();
	}

}
