package org.keegsands.sportball.api;

import java.util.List;

import org.keegsands.sportball.model.Team;
import org.keegsands.sportball.service.TeamService;
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
public class TeamApi {
	private static final String GET = "/api/teams";
	private static final String GET_BY_ID = "/api/teams/{teamId}";
	
	private static final Logger logger = LoggerFactory.getLogger(TeamApi.class);
	
	@Autowired
	private TeamService teamService;
	
	public TeamApi() {
		logger.error("Starting Team API");
	}

	@RequestMapping(value=GET, method = RequestMethod.GET)
	public ResponseEntity<List<Team>> list() {
		return new ResponseEntity<>(teamService.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Team> getById(@PathVariable int teamId) {
		final Team team = teamService.getById(teamId);
		return new ResponseEntity<>(team, HttpStatus.OK);
	}
}
