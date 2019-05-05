package org.keegsands.sportball.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.keegsands.sportball.model.Standing;
import org.keegsands.sportball.model.Team;

public class BaseballUtilTest {

	@Test
	public void testURIString(){
		try{
			final URI uri = new URI("postgresql://keegan:pwd@localhost:5432/keeg_master");
			Assert.assertEquals(uri.getHost(), "localhost");
			Assert.assertEquals(uri.getPort(), 5432);
			Assert.assertEquals(uri.getPath(), "/keeg_master");
			Assert.assertEquals(uri.getUserInfo().split(":")[0], "keegan");
			Assert.assertEquals(uri.getUserInfo().split(":")[1], "pwd");
		}catch (URISyntaxException exception){
			
		}
		
		
	}
	@Test
	public void testCalcGamesBack() {
		Assert.assertEquals(BaseballUtil.calculateGamesBack(0, 0, 0, 0), 0.0f);

		Assert.assertEquals(BaseballUtil.calculateGamesBack(1, 0, 0, 1), 1.0f);

		Assert.assertEquals(BaseballUtil.calculateGamesBack(5, 0, 0, 4), 4.5f);

		Assert.assertEquals(BaseballUtil.calculateGamesBack(1.5f, 0, 0, 1), 1.25f);
	}

	@Test
	public void testFixTieBreaker() {

		final List<Standing> standings = createSampleStandings();
		BaseballUtil.fixStandingsTieBreaker(standings);
		Assert.assertEquals(standings.size(), 6);
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
