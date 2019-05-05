package org.keegsands.sportball.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import org.keegsands.sportball.dao.PlayerDAO;
import org.keegsands.sportball.model.Player;
import org.keegsands.sportball.service.PlayerService;

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
