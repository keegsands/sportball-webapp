package org.keegsands.sportball.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.keegsands.sportball.dao.FieldDAO;
import org.keegsands.sportball.model.Field;

@Repository
public class FieldDAOImpl extends AbstractSimpleDAOImpl<Field> implements
		FieldDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FieldDAOImpl.class);
	private static final String FROM_CLAUSE = "from Field  ORDER by name ASC";

	private static final Class<Field> TYPE = Field.class;

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	String getFromClause() {
		return FROM_CLAUSE;
	}

	@Override
	protected Class<Field> getType() {

		return TYPE;
	}

}
