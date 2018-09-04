package com.bocano.softball.service.impl;

import org.springframework.stereotype.Service;

import com.bocano.softball.dao.GameTimeDAO;
import com.bocano.softball.model.GameTime;
import com.bocano.softball.service.GameTimeService;

@Service
public class GameTimeServiceImpl extends
		AbstractSimpleServiceImpl<GameTime, GameTimeDAO> implements
		GameTimeService {
	public void setGameTimeDAO(final GameTimeDAO gameTimeDAO) {
		super.setDAO(gameTimeDAO);
	}
}
