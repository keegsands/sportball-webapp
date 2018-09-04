package com.bocano.softball.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bocano.softball.dao.SimpleDAO;
import com.bocano.softball.service.SimpleService;

public class AbstractSimpleServiceImpl<G, D extends SimpleDAO<G>> implements
		SimpleService<G> {

	private D dao;

	protected void setDAO(D dao) {
		this.dao = dao;
	}

	protected D getDAO() {
		return this.dao;
	}

	@Transactional
	public void add(G p) {
		this.dao.add(p);
	}

	@Transactional
	public void update(G p) {
		this.dao.update(p);
	}

	@Transactional
	public List<G> list() {
		return this.dao.list();
	}

	@Transactional
	public G getById(int id) {
		return this.dao.getById(id);
	}

	@Transactional
	public void remove(int id) {
		this.dao.remove(id);
	}

}
