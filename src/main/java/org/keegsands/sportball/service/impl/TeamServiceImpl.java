package org.keegsands.sportball.service.impl;

import org.springframework.stereotype.Service;

import org.keegsands.sportball.dao.TeamDAO;
import org.keegsands.sportball.model.Team;
import org.keegsands.sportball.service.TeamService;

@Service
public class TeamServiceImpl extends AbstractSimpleServiceImpl<Team, TeamDAO>
		implements TeamService {
	public void setTeamDAO(final TeamDAO teamDao) {
		super.setDAO(teamDao);
	}
}
