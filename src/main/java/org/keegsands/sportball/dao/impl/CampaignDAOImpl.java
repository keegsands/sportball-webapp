package org.keegsands.sportball.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import org.keegsands.sportball.dao.CampaignDAO;
import org.keegsands.sportball.model.Campaign;

public class CampaignDAOImpl extends AbstractSimpleDAOImpl<Campaign> implements CampaignDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CampaignDAOImpl.class);
	private static final String FROM_CLAUSE = "from Campaign  ORDER by name ASC";
	
	/** Where condition for the find by season method */
	private static final String FIND_BY_SEASON_SQL = "from Campaign WHERE season.id = :seasonID ORDER by division_id";

	private static final Class<Campaign> TYPE = Campaign.class;
	
	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	String getFromClause() {
		return FROM_CLAUSE;
	}

	@Override
	protected Class<Campaign> getType() {

		return TYPE;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Campaign> findBySeason(final int seasonID) {
		final Session session = super.sessionFactory.getCurrentSession();
		final Query query = session.createQuery(FIND_BY_SEASON_SQL);
		query.setParameter("seasonID", seasonID);
		final List<Campaign> list = query.list();

		return list;
	}

}
