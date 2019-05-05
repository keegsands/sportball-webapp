package org.keegsands.sportball.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;

import org.keegsands.sportball.dao.SimpleDAO;

public abstract class AbstractSimpleDAOImpl<G> implements SimpleDAO<G> {

	protected SessionFactory sessionFactory;

	abstract protected Logger getLogger();

	abstract String getFromClause();

	abstract protected Class<G> getType();

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void add(G p) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		getLogger().info("Entity saved successfully, Entity Details=" + p);
	}

	public void update(G p) {
		final Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		getLogger().info("Entity updated successfully, Entity Details=" + p);

	}

	@SuppressWarnings("unchecked")
	public List<G> list() {
		final Session session = this.sessionFactory.getCurrentSession();
		final List<G> list = session.createQuery(getFromClause()).list();
		for (final G p : list) {
			getLogger().info("Team List::" + p);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public G getById(int id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final G p = (G) session.load(getType(), Integer.valueOf(id));
		getLogger().info("Entity loaded successfully, Entity details=" + p);
		return p;
	}

	@SuppressWarnings("unchecked")
	public void remove(int id) {
		final Session session = this.sessionFactory.getCurrentSession();
		final G p = (G) session.load(getType(), Integer.valueOf(id));
		if (null != p) {
			session.delete(p);
		}
		getLogger().info("Entity deleted successfully, Entity details=" + p);

	}

}
