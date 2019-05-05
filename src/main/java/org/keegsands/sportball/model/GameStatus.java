package org.keegsands.sportball.model;

import java.util.HashMap;
import java.util.Map;

public enum GameStatus {

	FINAL("Final", "F"), FINAL6("Final/6", "F/6"), FINAL5("Final/5", "F/5"), FINAL8("Final/8", "F/8"), PPD("Postponed", "PPD"), FORFEIT("Forfeit", "Forfeit"), CANCELLED("Cancelled", "Cancelled");

	private final String value;
	private final String shortValue;

	private static final Map<String, GameStatus> STATUS_MAP = new HashMap<String, GameStatus>();

	static {
		for (final GameStatus s : GameStatus.values()) {
			STATUS_MAP.put(s.getValue(), s);
		}
	}

	public static GameStatus get(final String value) {
		return STATUS_MAP.get(value);
	}

	private GameStatus(final String value, final String shortValue) {
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
