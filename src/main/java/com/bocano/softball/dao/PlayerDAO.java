package com.bocano.softball.dao;

import java.util.List;

import com.bocano.softball.model.Player;

public interface PlayerDAO extends SimpleDAO<Player> {
	List<Player> findByTeam(final int teamId);
}
