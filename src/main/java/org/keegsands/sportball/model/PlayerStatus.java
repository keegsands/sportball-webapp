package org.keegsands.sportball.model;

import java.util.HashMap;
import java.util.Map;

public enum PlayerStatus {
	ACTIVE("Active", "ACT"), FORMER("Former Player", "FP"), INJURED("Injured", "IR");

	private final String value;
	private final String shortValue;

	private static final Map<String, PlayerStatus> PLAYER_STATUS_MAP = new HashMap<String, PlayerStatus>();

	static {
		for (final PlayerStatus s : PlayerStatus.values()) {
			PLAYER_STATUS_MAP.put(s.getValue(), s);
		}
	}

	public static PlayerStatus get(final String value) {
		return PLAYER_STATUS_MAP.get(value);
	}

	private PlayerStatus(final String value, final String shortValue) {
		this.value = value;
		this.shortValue = shortValue;
	}

	public String getValue() {
		return value;
	}

	public String getShortValue() {
		return shortValue;
	}
}
