package org.keegsands.sportball.dao;

import java.util.List;

import org.keegsands.sportball.model.Player;

public interface PlayerDAO extends SimpleDAO<Player> {
	List<Player> findByTeam(final int teamId);
}
