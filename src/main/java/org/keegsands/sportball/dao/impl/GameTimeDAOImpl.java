package org.keegsands.sportball.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.keegsands.sportball.dao.GameTimeDAO;
import org.keegsands.sportball.model.GameTime;

@Repository
public class GameTimeDAOImpl extends AbstractSimpleDAOImpl<GameTime> implements
		GameTimeDAO {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GameTimeDAOImpl.class);

	private static final String FROM_CLAUSE = "from GameTime";

	private static final Class<GameTime> TYPE = GameTime.class;

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	String getFromClause() {
		return FROM_CLAUSE;
	}

	@Override
	protected Class<GameTime> getType() {

		return TYPE;
	}

}
