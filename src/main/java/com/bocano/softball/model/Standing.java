package com.bocano.softball.model;

import java.util.List;

import com.bocano.softball.util.BaseballUtil;

public class Standing implements Comparable<Standing> {
	private Team team;
	private int wins;
	private int losses;
	private int ties;
	private float gamesBack;
	private int streak;
	private List<Game> games;
	private Campaign campaign;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getTies() {
		return ties;
	}

	public void setTies(int ties) {
		this.ties = ties;
	}

	public float getGamesBack() {
		return gamesBack;
	}

	public void setGamesBack(float gamesBack) {
		this.gamesBack = gamesBack;
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	/**
	 * Initialize the win/loss/tie data based on the games
	 */
	public void initData() {
		int wins = 0;
		int losses = 0;
		int ties = 0;
		int streak = 0;
		for (final Game game : games) {
			// Count towards wins and losses only if game is complete
			if (game.isComplete()) {
				if (game.getAwayTeamRuns() == game.getHomeTeamRuns()) {
					ties++;
					streak = 0;
				} else if (BaseballUtil.didTeamWin(team, game)) {
					if (streak < 0) {
						streak = 0;
					}
					streak++;
					wins++;
				} else {
					if (streak > 0) {
						streak = 0;
					}
					streak--;
					losses++;
				}
			}
		}

		setWins(wins);
		setLosses(losses);
		setTies(ties);
		setStreak(streak);

	}

	public String getStreakText() {
		if (getStreak() > 0) {
			return "W" + getStreak();
		} else if (getStreak() < 0) {
			return "L" + -1 * getStreak();
		} else {
			return "T1";
		}
	}

	public int getGamesPlayed() {
		return getWins() + getLosses() + getTies();
	}

	/**
	 * Get the winning percentage as an integer
	 *
	 * @return int the number of wins divided by the number of games played
	 *         times 1000. If the number of games played is 0 then the value is
	 *         1000.
	 */
	public int getWinningPercent() {
		if (getGamesPlayed() == 0) {
			return 1000;
		}
		final Double rawWinPct = 1000 * (getWins() + .5 * getTies()) / getGamesPlayed();
		return Math.round(rawWinPct.floatValue());
	}

	/**
	 * Format the winning percentage to include a decimal point
	 *
	 * @return If the winning percentage is 0 return .000 and if it is over 1000
	 *         return 1.000
	 */
	public String getFormattedWinningPercent() {
		if (getWinningPercent() <= 0) {
			return ".000";
		} else if (getWinningPercent() >= 1000) {
			return "1.000";
		} else if (getWinningPercent() < 100) {
			return ".0" + getWinningPercent();
		} else {
			return "." + getWinningPercent();
		}

	}

	@Override
	public int compareTo(final Standing s2) {
		final Standing s1 = this;
		// First test winning percentage
		if (s1.getWinningPercent() > s2.getWinningPercent()) {
			return -1;
		}
		if (s1.getWinningPercent() < s2.getWinningPercent()) {
			return 1;
		}

		// Next test how many games have been played
		if (s1.getGamesPlayed() > s2.getGamesPlayed()) {
			if (s1.getWinningPercent() >= 100) {
				return -1;
			} else {
				return 1;
			}

		} else if (s1.getGamesPlayed() < s2.getGamesPlayed()) {
			if (s1.getWinningPercent() >= 100) {
				return 1;
			} else {
				return -1;
			}
		}

		// Go through the games of the first team and if they played
		// each other and the later team in the standings won the game then
		// switch them
		if (null != s1.getGames()) {
			for (final Game game : s1.getGames()) {

				if (game.isComplete()) {
					if (game.getAwayTeam().getId() == s2.getTeam().getId()) {
						if (game.getAwayTeamRuns() > game.getHomeTeamRuns()) {
							return 1;
						} else {
							return -1;
						}
					}
					if (game.getHomeTeam().getId() == s2.getTeam().getId()) {
						if (game.getHomeTeamRuns() > game.getAwayTeamRuns()) {
							return 1;
						} else {
							return -1;
						}
					}
				}

			}
		}
		return s1.getTeam().getName().compareTo(s2.getTeam().getName());
	}
}
