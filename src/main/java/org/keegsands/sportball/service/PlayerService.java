package org.keegsands.sportball.service;

import java.util.List;

import org.keegsands.sportball.model.Player;

public interface PlayerService extends SimpleService<Player> {

	List<Player> findByTeam(int teamId);
}
