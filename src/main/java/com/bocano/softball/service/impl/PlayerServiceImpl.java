package com.bocano.softball.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bocano.softball.dao.PlayerDAO;
import com.bocano.softball.model.Player;
import com.bocano.softball.service.PlayerService;

@Service
public class PlayerServiceImpl extends AbstractSimpleServiceImpl<Player, PlayerDAO> implements PlayerService {

	public void setPlayerDAO(final PlayerDAO playerDAO) {
		super.setDAO(playerDAO);
	}
	@Override
	public List<Player> findByTeam(int teamId) {
		return super.getDAO().findByTeam(teamId);
	}


}
