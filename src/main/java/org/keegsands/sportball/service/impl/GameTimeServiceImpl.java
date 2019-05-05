package org.keegsands.sportball.service.impl;

import org.springframework.stereotype.Service;

import org.keegsands.sportball.dao.GameTimeDAO;
import org.keegsands.sportball.model.GameTime;
import org.keegsands.sportball.service.GameTimeService;

@Service
public class GameTimeServiceImpl extends
		AbstractSimpleServiceImpl<GameTime, GameTimeDAO> implements
		GameTimeService {
	public void setGameTimeDAO(final GameTimeDAO gameTimeDAO) {
		super.setDAO(gameTimeDAO);
	}
}
