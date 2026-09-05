package org.keegsands.sportball.api;

import java.util.List;

import org.keegsands.sportball.model.Campaign;
import org.keegsands.sportball.model.Season;
import org.keegsands.sportball.model.Standing;
import org.keegsands.sportball.service.CampaignService;
import org.keegsands.sportball.service.SeasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeasonApi {
	private static final String GET = "/api/seasons";
	private static final String GET_BY_ID = "/api/seasons/{seasonId}";
	private static final String GET_STANDINGS_BY_ID = "/api/seasons/{seasonId}/standings";
	private static final String GET_CAMPAIGNS_BY_SEASON = "/api/seasons/{seasonId}/campaigns";
	
	private static final Logger logger = LoggerFactory.getLogger(SeasonApi.class);
	@Autowired
	private SeasonService seasonService;
	
	@Autowired
	private CampaignService campaignService;
	
	public SeasonApi() {
		logger.debug("Starting season API");
	}

	@RequestMapping(value=GET, method = RequestMethod.GET)
	public ResponseEntity<List<Season>> list() {
		return new ResponseEntity<>(seasonService.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Season> getById(@PathVariable int seasonId) {
		final Season season = seasonService.getById(seasonId);
		return new ResponseEntity<>(season, HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_STANDINGS_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<List<Standing>> getStandingsById(@PathVariable int seasonId) {
		final List<Standing> seasons = seasonService.getStandings(seasonId);
		return new ResponseEntity<>(seasons, HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_CAMPAIGNS_BY_SEASON, method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getCampaignsById(@PathVariable int seasonId) {
		final List<Campaign> campaigns = campaignService.findBySeason(seasonId);
		return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
}

