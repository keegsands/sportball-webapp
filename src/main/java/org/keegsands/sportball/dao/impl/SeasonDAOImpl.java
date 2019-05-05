package org.keegsands.sportball.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.keegsands.sportball.dao.SeasonDAO;
import org.keegsands.sportball.model.Season;

@Repository
public class SeasonDAOImpl extends AbstractSimpleDAOImpl<Season> implements
		SeasonDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SeasonDAOImpl.class);
	private static final String FROM_CLAUSE = "from Season ORDER by name DESC";

	private static final Class<Season> TYPE = Season.class;

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	String getFromClause() {
		return FROM_CLAUSE;
	}

	@Override
	protected Class<Season> getType() {

		return TYPE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Season> listStandingSeasons() {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<Season> list = session.createQuery("from Season WHERE standingsSupport = true ORDER by name DESC").list();
		for (final Season p : list) {
			getLogger().info("Team List::" + p);
		}
		return list;
	}

}
