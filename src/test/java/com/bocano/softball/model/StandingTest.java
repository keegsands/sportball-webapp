package com.bocano.softball.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StandingTest {
	@Test
	public void testSortStandings() {
		final List<Standing> standings = createSampleStandings();
		Collections.sort(standings);

		Assert.assertEquals(standings.get(0).getTeam().getName(), "Team6");
		Assert.assertEquals(standings.get(1).getTeam().getName(), "Team1");
		Assert.assertEquals(standings.get(2).getTeam().getName(), "Team2");
		Assert.assertEquals(standings.get(3).getTeam().getName(), "Team5");
		Assert.assertEquals(standings.get(4).getTeam().getName(), "Team4");
		Assert.assertEquals(standings.get(5).getTeam().getName(), "Team3");

	}

	private List<Standing> createSampleStandings() {
		final List<Standing> standings = new ArrayList<Standing>();

		standings.add(createStanding("Team1", 4, 4));
		standings.add(createStanding("Team2", 4, 4));
		standings.add(createStanding("Team3", 3, 4));
		standings.add(createStanding("Team4", 2, 2));
		standings.add(createStanding("Team5", 4, 4));
		standings.add(createStanding("Team6", 5, 4));
		return standings;

	}

	private static Standing createStanding(final String teamName, final int wins, final int losses) {
		final Standing standing = new Standing();
		standing.setTeam(createTeam(teamName));
		standing.setWins(wins);
		standing.setLosses(losses);
		return standing;
	}

	private static Team createTeam(final String name) {
		final Team team = new Team();
		team.setName(name);
		return team;
	}
}
