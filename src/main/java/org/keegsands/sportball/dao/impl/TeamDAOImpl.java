package org.keegsands.sportball.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.keegsands.sportball.dao.TeamDAO;
import org.keegsands.sportball.model.Team;

@Repository
public class TeamDAOImpl extends AbstractSimpleDAOImpl<Team> implements TeamDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TeamDAOImpl.class);
	private static final String FROM_CLAUSE = "from Team ORDER by name ASC";

	private static final Class<Team> TYPE = Team.class;

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	String getFromClause() {
		return FROM_CLAUSE;
	}

	@Override
	protected Class<Team> getType() {

		return TYPE;
	}

}
