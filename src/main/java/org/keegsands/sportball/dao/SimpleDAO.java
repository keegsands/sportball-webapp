package org.keegsands.sportball.dao;

import java.util.List;

public interface SimpleDAO<G> {
	void add(G p);

	void update(G p);

	List<G> list();

	G getById(int id);

	public void remove(int id);
}
