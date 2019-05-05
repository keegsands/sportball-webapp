package org.keegsands.sportball.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.keegsands.sportball.dao.PlayerDAO;
import org.keegsands.sportball.model.Player;

@Repository
public class PlayerDAOImpl extends AbstractSimpleDAOImpl<Player> implements PlayerDAO {
	private static final String FIND_BY_TEAM_SQL = "from Player WHERE team.id = :teamId";
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SeasonDAOImpl.class);
	private static final String FROM_CLAUSE = "from Player";

	private static final Class<Player> TYPE = Player.class;

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	String getFromClause() {
		return FROM_CLAUSE;
	}

	@Override
	protected Class<Player> getType() {

		return TYPE;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Player> findByTeam(final int teamId) {
		final Session session = super.sessionFactory.getCurrentSession();
		final Query query = session.createQuery(FIND_BY_TEAM_SQL);
		query.setParameter("teamId", teamId);
		final List<Player> list = query.list();

		return list;
	}

}
