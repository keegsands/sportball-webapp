package org.keegsands.sportball.dao;

import java.util.List;

import org.keegsands.sportball.model.Game;

public interface GameDAO extends SimpleDAO<Game> {

	List<Game> listNextTenGames();

	List<Game> findByTeamAndSeason(final int teamID, final int seasonID);
	
	List<Game> findBySeason(final int seasonID);

	void updateScore(final int gameID, final int homeTeamRuns, final int awayTeamRuns, final boolean complete, final String status);
}
