package org.keegsands.sportball.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusTest {

	@Test
	public void testShortStatus() {
		Assert.assertEquals(GameStatus.FINAL.getShortValue(), "F");
	}
}
