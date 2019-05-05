package org.keegsands.sportball.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tap_softball.campaign")
public class Campaign extends AbstractNamedEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToOne
	@JoinColumn(name = "division_id")
	private Division division;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	/**
	 * Get the Season for the campaign
	 *
	 * @return Season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Set the season for the campaign
	 *
	 * @param season
	 *            Season
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Get the team for the campaign
	 *
	 * @return Team team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Set the team for the campaign
	 *
	 * @param team
	 *            Team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * Get the division for the campaign
	 *
	 * @return Division
	 */
	public Division getDivision() {
		return division;
	}

	/**
	 * Set the division for the campaign
	 *
	 * @param division Division
	 */
	public void setDivision(Division division) {
		this.division = division;
	}

	

	@Override
	public String toString() {
		return "id=" + getId() ;
	}
}
