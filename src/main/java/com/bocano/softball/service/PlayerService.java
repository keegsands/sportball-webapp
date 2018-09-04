package com.bocano.softball.service;

import java.util.List;

import com.bocano.softball.model.Player;

public interface PlayerService extends SimpleService<Player> {

	List<Player> findByTeam(int teamId);
}
