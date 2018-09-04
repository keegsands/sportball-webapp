package com.bocano.softball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bocano.softball.model.Team;
import com.bocano.softball.service.TeamService;

@Controller
public class TeamController {
	private TeamService teamService;

	@Autowired(required = true)
	@Qualifier(value = "teamService")
	public void setTeamService(TeamService ps) {
		this.teamService = ps;
	}

	@RequestMapping(value = "/admin/teams", method = RequestMethod.GET)
	public String listTeams(Model model) {
		model.addAttribute("team", new Team());
		model.addAttribute("listTeams", this.teamService.list());
		return "team";
	}

	// For add and update team both
	@RequestMapping(value = "/admin/team/add", method = RequestMethod.POST)
	public String addTeam(@ModelAttribute("team") Team p) {

		if (p.getId() == 0) {
			// new team, add it
			this.teamService.add(p);
		} else {
			// existing team, call update
			this.teamService.update(p);
		}

		return "redirect:/admin/teams";

	}

	@RequestMapping("/admin/team/remove/{id}")
	public String removeTeam(@PathVariable("id") int id) {

		this.teamService.remove(id);
		return "redirect:/admin/teams";
	}

	@RequestMapping("/admin/team/edit/{id}")
	public String editTeam(@PathVariable("id") int id, Model model) {
		model.addAttribute("team", this.teamService.getById(id));
		model.addAttribute("listTeams", this.teamService.list());
		return "team";
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String adminPage() {

		return "admin";

	}
}
