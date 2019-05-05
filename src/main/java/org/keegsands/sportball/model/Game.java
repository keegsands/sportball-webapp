package org.keegsands.sportball.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tap_softball.game")
public class Game {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("M/dd/yyyy");
	private static final DateFormat FULL_FORMAT = new SimpleDateFormat("EEE, MMM dd, yyyy");

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "original_date")
	@Type(type = "date")
	private Date originalDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "scheduled_date")
	@Type(type = "date")
	private Date scheduledDate;

	private boolean complete = false;

	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;

	@ManyToOne
	@JoinColumn(name = "away_team_id")
	private Team awayTeam;

	@ManyToOne
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;

	@ManyToOne
	@JoinColumn(name = "field_id")
	private Field field;

	@ManyToOne
	@JoinColumn(name = "time_id")
	private GameTime gameTime;

	@Column(name = "home_team_runs")
	private int homeTeamRuns;

	@Column(name = "away_team_runs")
	private int awayTeamRuns;

	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the originally scheduled date for the game
	 *
	 * @return Date the original date of the game
	 */
	public Date getOriginalDate() {
		return originalDate;
	}

	/**
	 * Set the originally scheduled date for the game
	 *
	 * @param originalDate
	 *            Date
	 */
	public void setOriginalDate(Date originalDate) {
		this.originalDate = originalDate;
	}

	/**
	 * Get the scheduled date for the game. This may be later than the original
	 * date
	 *
	 * @return Date the date the game is actually played
	 */
	public Date getScheduledDate() {
		return scheduledDate;
	}

	/**
	 * Set the scheduled date for the game. When it will be played
	 *
	 * @param scheduledDate
	 *            Date the date the game will be played
	 */
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	/**
	 * Has the game been completed
	 *
	 * @return boolean true if the game has been completed
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * Set the completed state of the game
	 *
	 * @param complete
	 *            boolean true if the game is complete
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**
	 * Get the status of the current game
	 *
	 * @return String defaults to null
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the status for the game
	 *
	 * @param status
	 *            String
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the Season the game occurred in
	 *
	 * @return Season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Set the season the game was played in
	 *
	 * @param season
	 *            Season
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Get the home team for the game
	 *
	 * @return Team home team
	 */
	public Team getHomeTeam() {
		return homeTeam;
	}

	/**
	 * Set the home team for the game
	 *
	 * @param homeTeam
	 *            Team
	 */
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * Get the away team for the game
	 *
	 * @return Team away team
	 */
	public Team getAwayTeam() {
		return awayTeam;
	}

	/**
	 * Set the away team for the game
	 *
	 * @param awayTeam
	 *            Team
	 */
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	/**
	 * Get the field where the game is to be played
	 *
	 * @return Field field where the game is played
	 */
	public Field getField() {
		return field;
	}

	/**
	 * Set the field where the game will be played
	 *
	 * @param field
	 *            Field
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * Get the game time for the game
	 *
	 * @return GameTime
	 */
	public GameTime getGameTime() {
		return gameTime;
	}

	/**
	 * Set the game time for the game
	 *
	 * @param gameTime
	 *            GameTime
	 */
	public void setGameTime(GameTime gameTime) {
		this.gameTime = gameTime;
	}

	public int getHomeTeamRuns() {
		return homeTeamRuns;
	}

	public void setHomeTeamRuns(int homeTeamRuns) {
		this.homeTeamRuns = homeTeamRuns;
	}

	public int getAwayTeamRuns() {
		return awayTeamRuns;
	}

	public void setAwayTeamRuns(int awayTeamRuns) {
		this.awayTeamRuns = awayTeamRuns;
	}

	/**
	 * Get the Original Date of the game formatted to a String
	 *
	 * @return String Date in m/dd/yyyy format
	 */
	public String getFormattedOriginalDate() {
		if (null != getOriginalDate()) {
			return DATE_FORMAT.format(getOriginalDate());
		}
		return "";
	}

	/**
	 * Get the Scheduled Date of the game formatted to a String
	 *
	 * @return String Date in m/dd/yyyy format
	 */
	public String getFormattedScheduledDate() {
		if (null != getScheduledDate()) {
			return DATE_FORMAT.format(getScheduledDate());
		}
		return "";
	}

	/**
	 * Get a full version of the scheduled date which includes the text for day
	 * of the week and month
	 *
	 * @return String Date in EEE MMM dd, yyyy format
	 */
	public String getFullScheduledDate() {
		if (null != getScheduledDate()) {
			return FULL_FORMAT.format(getScheduledDate());
		}
		return "";
	}

	/**
	 * Get the score of the game with the higher number of runs first separated
	 * by a hyphen
	 *
	 * @return String runs scored separated by a hyphen. If the game is not
	 *         complete just an empty string will be returned.
	 */
	public String getHyphenScore() {
		if (isComplete()) {
			return Math.max(getHomeTeamRuns(), getAwayTeamRuns()) + "-" + Math.min(getHomeTeamRuns(), getAwayTeamRuns());
		} else {
			return "";
		}
	}

	public String getShortStatus() {
		final GameStatus theStatus = GameStatus.get(getStatus());
		if (null == theStatus) {
			return getStatus();
		} else {
			return theStatus.getShortValue();
		}

	}

	@Override
	public String toString() {
		return "id=" + getId() + ", complete=" + isComplete();
	}
}
