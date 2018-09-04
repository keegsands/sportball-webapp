package com.bocano.softball.dao;

import java.util.List;

import com.bocano.softball.model.Season;

public interface SeasonDAO extends SimpleDAO<Season> {
	List<Season> listStandingSeasons();
}
