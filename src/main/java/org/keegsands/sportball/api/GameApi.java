package org.keegsands.sportball.api;

import java.util.List;

import org.keegsands.sportball.model.Game;
import org.keegsands.sportball.service.GameService;
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
public class GameApi {
	private static final String GET = "/api/games";
	private static final String GET_BY_ID = "/api/games/{gameId}";
	
	private static final String GET_BY_SEASON = "/api/seasons/{seasonId}/games";
	private static final String GET_BY_SEASON_TEAM = "/api/seasons/{seasonId}/teams/{teamId}/games";
	
	private static final Logger logger = LoggerFactory.getLogger(GameApi.class);
	
	@Autowired
	private GameService gameService;
	
	public GameApi() {
		logger.error("Starting Game API");
	}

	@RequestMapping(value=GET, method = RequestMethod.GET)
	public ResponseEntity<List<Game>> list() {
		return new ResponseEntity<>(gameService.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Game> getById(@PathVariable int gameId) {
		final Game game = gameService.getById(gameId);
		return new ResponseEntity<>(game, HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_BY_SEASON, method = RequestMethod.GET)
	public ResponseEntity<List<Game>> listBySeason(@PathVariable int seasonId) {
		return new ResponseEntity<>(gameService.findBySeason(seasonId), HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_BY_SEASON_TEAM, method = RequestMethod.GET)
	public ResponseEntity<List<Game>> listBySeasonAndTeam(@PathVariable int seasonId, @PathVariable int teamId) {
		return new ResponseEntity<>(gameService.findByTeamAndSeason(teamId, seasonId), HttpStatus.OK);
	}

}
