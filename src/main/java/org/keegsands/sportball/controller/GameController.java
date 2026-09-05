package org.keegsands.sportball.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.keegsands.sportball.model.Field;
import org.keegsands.sportball.model.Game;
import org.keegsands.sportball.model.GameTime;
import org.keegsands.sportball.model.Season;
import org.keegsands.sportball.model.GameStatus;
import org.keegsands.sportball.model.Team;
import org.keegsands.sportball.propertyeditor.BaseEditor;
import org.keegsands.sportball.service.FieldService;
import org.keegsands.sportball.service.GameService;
import org.keegsands.sportball.service.GameTimeService;
import org.keegsands.sportball.service.MailService;
import org.keegsands.sportball.service.SeasonService;
import org.keegsands.sportball.service.SimpleService;
import org.keegsands.sportball.service.TeamService;

@Controller
public class GameController {
	private static final String PEASE_SOFTBALL_EMAIL = "peasesoftball@gmail.com";
	private static final String SCORE_UPDATE_SUBJECT = "TAP Softball Update";
	private static final String[] DEFAULT_EMAIL_TO = { "keegsands@gmail.com", "TPDonovan@tigheBond.com" };

	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

	private GameService gameService;
	private TeamService teamService;
	private SeasonService seasonService;
	private FieldService fieldService;
	private GameTimeService gameTimeService;
	private MailService mailService;

	@Autowired(required = true)
	@Qualifier(value = "gameService")
	public void setGameService(GameService ps) {
		this.gameService = ps;
	}

	@Autowired(required = true)
	@Qualifier(value = "teamService")
	public void setTeamService(TeamService ts) {
		this.teamService = ts;
	}

	@Autowired(required = true)
	@Qualifier(value = "seasonService")
	public void setSeasonService(SeasonService ts) {
		this.seasonService = ts;
	}

	@Autowired(required = true)
	@Qualifier(value = "fieldService")
	public void setFieldService(FieldService ts) {
		this.fieldService = ts;
	}

	@Autowired(required = true)
	@Qualifier(value = "gameTimeService")
	public void setGameTimeService(GameTimeService ts) {
		this.gameTimeService = ts;
	}

	@Autowired(required = true)
	@Qualifier(value = "mailService")
	public void setMailService(MailService ms) {
		this.mailService = ms;
	}

	@RequestMapping(value = "/admin/games", method = RequestMethod.GET)
	public String listGames(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("listGames", this.gameService.list());
		model.addAttribute("listTeams", this.teamService.list());
		model.addAttribute("listSeasons", this.seasonService.list());
		model.addAttribute("listFields", this.fieldService.list());
		model.addAttribute("listTimes", this.gameTimeService.list());
		model.addAttribute("listStatus", GameStatus.class.getEnumConstants());
		return "game";
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String listSchedule(Model model) {
		model.addAttribute("listGames", this.gameService.findBySeason(this.seasonService.getCurrentSeason().getId()));

		return "schedule";
	}

	@RequestMapping(value = "/schedule/{teamID}", method = RequestMethod.GET)
	public String listSchedule(@PathVariable("teamID") int teamID, Model model) {
		model.addAttribute("listGames", this.gameService.findByTeamAndSeason(teamID, this.seasonService.getCurrentSeason().getId()));
		model.addAttribute("team", teamService.getById(teamID));
		return "teamSchedule";
	}

	@RequestMapping(value = "/scores", method = RequestMethod.GET)
	public String listNextTenGames(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("listGames", this.gameService.listNextTenGames());
		model.addAttribute("listTeams", this.teamService.list());
		model.addAttribute("listSeasons", this.seasonService.list());
		model.addAttribute("listFields", this.fieldService.list());
		model.addAttribute("listTimes", this.gameTimeService.list());
		return "score";
	}

	@RequestMapping("/scores/edit/{id}")
	public String updateScore(@PathVariable("id") int id, Model model) {
		model.addAttribute("game", this.gameService.getById(id));
		model.addAttribute("listTeams", this.teamService.list());
		model.addAttribute("listStatus", GameStatus.class.getEnumConstants());
		return "score";
	}

	@RequestMapping(value = "/scores/edit", method = RequestMethod.POST)
	public String updateScore(@ModelAttribute("game") Game p) {

		if (p.isComplete() && null == p.getStatus()) {
			p.setStatus(GameStatus.FINAL.getValue());
		}
		this.gameService.updateScore(p.getId(), p.getHomeTeamRuns(), p.getAwayTeamRuns(), p.isComplete(), p.getStatus());

		try {
			sendUpdateScoreEmail(p);
		} catch (final Exception e) {
			LOGGER.error("Failed to send the score update email");
		}

		return "redirect:/scores";

	}

	/**
	 * Send the update score email
	 *
	 * @param p
	 *            Game that was being updated
	 */
	private void sendUpdateScoreEmail(final Game p) {
		final Game originalGame = this.gameService.getById(p.getId());
		p.setHomeTeam(originalGame.getHomeTeam());
		p.setAwayTeam(originalGame.getAwayTeam());

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String updaterName = auth.getName();

		this.mailService.sendMessage(PEASE_SOFTBALL_EMAIL, DEFAULT_EMAIL_TO, generateEmailSubject(p, updaterName), generateEmailBody(p, updaterName));
	}

	private String generateEmailSubject(final Game p, final String updater) {
		final StringBuilder subjectBuilder = new StringBuilder();
		subjectBuilder.append(SCORE_UPDATE_SUBJECT + ": ");
		subjectBuilder.append(p.getAwayTeam().getName() + " vs. " + p.getHomeTeam().getName());

		return subjectBuilder.toString();
	}

	/**
	 * Generate the body for the update score email
	 *
	 * @param p
	 *            Game
	 * @param updater
	 *            String name fo the updater
	 * @return String with the message body
	 */
	private String generateEmailBody(final Game p, final String updater) {
		final StringBuilder msgBuilder = new StringBuilder();
		msgBuilder.append(p.getAwayTeam().getName() + " " + p.getAwayTeamRuns() + "\n");
		msgBuilder.append(p.getHomeTeam().getName() + " " + p.getHomeTeamRuns() + "\n");
		msgBuilder.append("The current status is " + p.getStatus() + "\n");
		msgBuilder.append("The score was updated by : " + updater + "\n");
		
		return msgBuilder.toString();

	}

	// For add and update game both
	@RequestMapping(value = "/admin/game/add", method = RequestMethod.POST)
	public String addGame(@ModelAttribute("game") Game p) {

		if (p.getId() == 0) {
			// new game, add it
			this.gameService.add(p);
		} else {
			// existing game, call update
			this.gameService.update(p);
		}

		return "redirect:/admin/games";

	}

	@RequestMapping("/admin/game/remove/{id}")
	public String removeGame(@PathVariable("id") int id) {

		this.gameService.remove(id);
		return "redirect:/admin/games";
	}

	@RequestMapping("/admin/game/edit/{id}")
	public String editGame(@PathVariable("id") int id, Model model) {
		model.addAttribute("game", this.gameService.getById(id));
		// model.addAttribute("listGames", this.gameService.list());
		model.addAttribute("listTeams", this.teamService.list());
		model.addAttribute("listSeasons", this.seasonService.list());
		model.addAttribute("listFields", this.fieldService.list());
		model.addAttribute("listTimes", this.gameTimeService.list());
		model.addAttribute("listStatus", GameStatus.class.getEnumConstants());
		return "game";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Field.class, new BaseEditor<Field, SimpleService<Field>>(this.fieldService));
		binder.registerCustomEditor(Season.class, new BaseEditor<Season, SimpleService<Season>>(this.seasonService));
		binder.registerCustomEditor(Team.class, new BaseEditor<Team, SimpleService<Team>>(this.teamService));
		binder.registerCustomEditor(GameTime.class, new BaseEditor<GameTime, SimpleService<GameTime>>(this.gameTimeService));
	}
}