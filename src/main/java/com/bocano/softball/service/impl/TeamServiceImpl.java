package com.bocano.softball.service.impl;

import org.springframework.stereotype.Service;

import com.bocano.softball.dao.TeamDAO;
import com.bocano.softball.model.Team;
import com.bocano.softball.service.TeamService;

@Service
public class TeamServiceImpl extends AbstractSimpleServiceImpl<Team, TeamDAO>
		implements TeamService {
	public void setTeamDAO(final TeamDAO teamDao) {
		super.setDAO(teamDao);
	}
}
