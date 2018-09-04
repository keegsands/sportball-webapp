package com.bocano.softball.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bocano.softball.dao.GameDAO;
import com.bocano.softball.model.Game;
import com.bocano.softball.service.GameService;

@Service
public class GameServiceImpl extends AbstractSimpleServiceImpl<Game, GameDAO> implements GameService {
	public void setGameDAO(final GameDAO gameDao) {
		super.setDAO(gameDao);
	}

	@Override
	public List<Game> listNextTenGames() {
		return getDAO().listNextTenGames();
	}

	@Override
	public List<Game> findByTeamAndSeason(int teamID, int seasonID) {
		return getDAO().findByTeamAndSeason(teamID, seasonID);
	}

	@Override
	public void updateScore(final int gameID, final int homeTeamRuns, final int awayTeamRuns, final boolean complete, final String status) {
		getDAO().updateScore(gameID, homeTeamRuns, awayTeamRuns, complete, status);

	}

	@Override
	public List<Game> findBySeason(int seasonID) {
		return getDAO().findBySeason(seasonID);
	}

}
