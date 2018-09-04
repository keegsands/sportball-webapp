package com.bocano.softball.service;

import java.util.List;

import com.bocano.softball.model.Season;
import com.bocano.softball.model.Standing;

public interface SeasonService extends SimpleService<Season> {

	/**
	 * Get the standings for the specified season
	 * 
	 * @param seasonID
	 * @return
	 */
	List<Standing> getStandings(int seasonID);
	
	List<Season> listStandingSeasons();
}
