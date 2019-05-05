package org.keegsands.sportball.util;

import java.util.List;

import org.keegsands.sportball.model.Game;
import org.keegsands.sportball.model.Standing;
import org.keegsands.sportball.model.Team;

public class BaseballUtil {

	public static final int CURRENT_SEASON = 13;
	/**
	 * Calculate the number of games back for team 2
	 *
	 * @param team1Wins
	 *            int number of wins for team 1
	 * @param team1Losses
	 *            int number of losses for team 1
	 * @param team2Wins
	 *            int number of wins for team 2
	 * @param team2Losses
	 *            int number of losses for team 2
	 * @return float The average of the difference between team 1 and team 2
	 *         wins and team2 losses and team 2 losses.
	 */
	public static float calculateGamesBack(float team1Wins, int team1Losses, float team2Wins, int team2Losses) {
		return ((team1Wins - team2Wins) + (team2Losses - team1Losses)) / 2;
	}

	/**
	 * Test to see if the passed in team won the game based. Test to see if the
	 * team was the home team and then see if home had more runs than away. If
	 * not home then test to see if the team was the away team an then see if
	 * away had more runs than home.
	 *
	 * @param team
	 *            Team that is being evaluated
	 * @param game
	 *            Game that is being evaluated
	 * @return true if the team was the home team and they had more runs than
	 *         the away team true if the team was the away team and they had
	 *         more runs than the home team otherwise return false
	 * @throws IllegalArgumentException
	 *             if the passed in team wasn't the home or away team
	 */
	public static boolean didTeamWin(final Team team, final Game game) {

		if (game.getHomeTeam().equals(team)) {
			return game.getHomeTeamRuns() > game.getAwayTeamRuns();
		} else if (game.getAwayTeam().equals(team)) {
			return game.getAwayTeamRuns() > game.getHomeTeamRuns();
		}
		throw new IllegalArgumentException("The team being tested didn't play in this game");
	}

	public static void fixStandingsTieBreaker(final List<Standing> standings) {

		// Set start values
		int currentPct = 0;
		int percentCtr = 0;

		// Iterate through all the standings
		for (int ctr = 0; ctr < standings.size(); ctr++) {
			final Standing standing = standings.get(ctr);
			// If the winning percentage of this one is the same as the current
			// winning percentage up the counter and move on
			if (currentPct == standing.getWinningPercent()) {
				percentCtr++;
			} else {
				// If they aren't the same and two teams are tied then
				// handle their tie breaker
				if (percentCtr == 3) {
					// Place holder for three way tie breaker
					// Get the two previous standings
					/*
					 * final Standing otherStanding1 = standings.get(ctr - 3);
					 * final Standing otherStanding2 = standings.get(ctr - 2);
					 * final Standing otherStanding3 = standings.get(ctr - 1);
					 */

				}
				percentCtr = 1;
				currentPct = standing.getWinningPercent();
			}
		}

	}
}
