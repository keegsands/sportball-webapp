package com.bocano.softball.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bocano.softball.model.Player;
import com.bocano.softball.model.Team;
import com.bocano.softball.propertyeditor.BaseEditor;
import com.bocano.softball.service.PlayerService;
import com.bocano.softball.service.SimpleService;
import com.bocano.softball.service.TeamService;

@Controller
public class PlayerController {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);
	
	private PlayerService playerService;
	private TeamService teamService;
	
	@Autowired(required = true)
	@Qualifier(value = "playerService")
	public void setPlayerService(PlayerService ps) {
		this.playerService = ps;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "teamService")
	public void setTeamService(TeamService ts) {
		this.teamService = ts;
	}

	@RequestMapping(value = "/admin/players", method = RequestMethod.GET)
	public String listSeasons(Model model) {
		model.addAttribute("player", new Player());
		model.addAttribute("listPlayers", this.playerService.list());
		model.addAttribute("listTeams", this.teamService.list());
		return "player";
	}

	// For add and update season both
	@RequestMapping(value = "/admin/player/add", method = RequestMethod.POST)
	public String addPlayer(@ModelAttribute("player") Player p) {

		if (p.getId() == 0) {
			// new season, add it
			this.playerService.add(p);
		} else {
			// existing season, call update
			this.playerService.update(p);
		}

		return "redirect:/admin/players";

	}

	@RequestMapping("/admin/player/remove/{id}")
	public String removePlayer(@PathVariable("id") int id) {

		this.playerService.remove(id);
		return "redirect:/admin/players";
	}

	@RequestMapping("/admin/player/edit/{id}")
	public String editSeason(@PathVariable("id") int id, Model model) {
		model.addAttribute("player", this.playerService.getById(id));
		model.addAttribute("listPlayers", this.playerService.list());
		model.addAttribute("listTeams", this.teamService.list());
		return "player";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Team.class, new BaseEditor<Team, SimpleService<Team>>(this.teamService));
	}

}
