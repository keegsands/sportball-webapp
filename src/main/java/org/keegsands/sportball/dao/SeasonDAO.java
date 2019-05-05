package org.keegsands.sportball.dao;

import java.util.List;

import org.keegsands.sportball.model.Season;

public interface SeasonDAO extends SimpleDAO<Season> {
	List<Season> listStandingSeasons();
}
