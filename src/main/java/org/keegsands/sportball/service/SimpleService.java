package org.keegsands.sportball.service;

import java.util.List;

public interface SimpleService<G> {
	void add(G p);

	void update(G p);

	List<G> list();

	G getById(int id);

	void remove(int id);
}
