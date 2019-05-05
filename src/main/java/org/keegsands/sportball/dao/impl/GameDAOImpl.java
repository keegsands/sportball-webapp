package org.keegsands.sportball.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.keegsands.sportball.dao.GameDAO;
import org.keegsands.sportball.model.Game;

@Repository
public class GameDAOImpl extends AbstractSimpleDAOImpl<Game> implements GameDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(GameDAOImpl.class);
	
	private static final String FROM_CLAUSE = "from Game ORDER by scheduledDate, gameTime.timeSlot ASC";
	
	/** Where condition for the find by season method */
	private static final String FIND_BY_SEASON_SQL = "from Game WHERE season.id = :seasonID ORDER by scheduledDate, gameTime.timeSlot ASC";
	
	/** Where condition for the list 10 games method */
	private static final String TEN_GAMES_SQL = "from Game WHERE complete = false OR scheduledDate >= :lastWeekDate ORDER by scheduledDate, gameTime.timeSlot ASC";
	
	/** Where condition for the find by team and season method */
	private static final String FIND_BY_TEAM_AND_SEASON_SQL = "from Game WHERE season.id = :seasonID AND (homeTeam.id = :teamID OR awayTeam.id = :teamID) ORDER by scheduledDate, gameTime.timeSlot ASC";

	private static final Class<Game> TYPE = Game.class;

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	String getFromClause() {
		return FROM_CLAUSE;
	}

	@Override
	protected Class<Game> getType() {

		return TYPE;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Game> listNextTenGames() {
		final Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -3);
		} else {
			calendar.add(Calendar.DATE, -1);
		}

		final Session session = super.sessionFactory.getCurrentSession();
		final Query query = session.createQuery(TEN_GAMES_SQL);
		query.setDate("lastWeekDate", calendar.getTime());
		query.setMaxResults(12);
		final List<Game> list = query.list();
		
		return list;
	}
	
	

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Game> findByTeamAndSeason(final int teamID, final int seasonID) {
		final Session session = super.sessionFactory.getCurrentSession();
		final Query query = session.createQuery(FIND_BY_TEAM_AND_SEASON_SQL);
		query.setParameter("seasonID", seasonID);
		query.setParameter("teamID", teamID);
		final List<Game> list = query.list();
		
		return list;
	}

	@Override
	@Transactional
	public void updateScore(final int gameID, final int homeTeamRuns, final int awayTeamRuns, final boolean complete, final String status) {
		final Game game = getById(gameID);
		game.setHomeTeamRuns(homeTeamRuns);
		game.setAwayTeamRuns(awayTeamRuns);
		game.setComplete(complete);
		game.setStatus(status);
		final Session session = super.sessionFactory.getCurrentSession();
		session.update(game);
		LOGGER.info("Updating score for game id: " + gameID + " Home team runs = " + homeTeamRuns + " away team runs = " + awayTeamRuns);
	}

	 
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Game> findBySeason(final int seasonID) {
		final Session session = super.sessionFactory.getCurrentSession();
		final Query query = session.createQuery(FIND_BY_SEASON_SQL);
		query.setParameter("seasonID", seasonID);
		final List<Game> list = query.list();

		return list;
	}

}
