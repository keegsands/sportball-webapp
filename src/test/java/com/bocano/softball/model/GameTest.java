package com.bocano.softball.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GameTest {

	@Test
	public void testHyphenScore() {
		final Game game = new Game();
		game.setComplete(false);
		Assert.assertEquals(game.getHyphenScore(), "");
		game.setComplete(true);
		Assert.assertEquals(game.getHyphenScore(), "0-0");
		game.setAwayTeamRuns(5);
		game.setHomeTeamRuns(3);
		Assert.assertEquals(game.getHyphenScore(), "5-3");

		game.setAwayTeamRuns(2);
		Assert.assertEquals(game.getHyphenScore(), "3-2");

	}

	@Test
	public void testShortStatus() {
		final Game game = new Game();

		Assert.assertNull(game.getShortStatus());
		game.setStatus("foo");
		Assert.assertEquals(game.getShortStatus(), game.getStatus());
		game.setStatus(GameStatus.FINAL5.getValue());
		Assert.assertEquals(game.getShortStatus(), GameStatus.FINAL5.getShortValue());

	}
}
