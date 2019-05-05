package org.keegsands.sportball.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayerTest {
	@Test
	public void testFullName() {
		final Player player = new Player();
		
		Assert.assertEquals(player.getFullName(), "");
		Assert.assertEquals(player.getShortName(), "");
		
		player.setFirstName("Keegan");
		Assert.assertEquals(player.getFullName(), "Keegan");
		Assert.assertEquals(player.getShortName(), "Keegan");
		
		player.setLastName("Sands");
		
		Assert.assertEquals(player.getFullName(), "Keegan Sands");
		Assert.assertEquals(player.getShortName(), "Keegan S");
		

	}
}
