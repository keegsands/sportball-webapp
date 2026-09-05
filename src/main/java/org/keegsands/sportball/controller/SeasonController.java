package org.keegsands.sportball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.keegsands.sportball.model.Division;
import org.keegsands.sportball.model.Season;
import org.keegsands.sportball.model.Standing;
import org.keegsands.sportball.service.SeasonService;
import org.keegsands.sportball.util.BaseballUtil;

@Controller
public class SeasonController {
	private SeasonService seasonService;

	@Autowired(required = true)
	@Qualifier(value = "seasonService")
	public void setSeasonService(SeasonService ps) {
		this.seasonService = ps;
	}

	@RequestMapping(value = "/admin/seasons", method = RequestMethod.GET)
	public String listSeasons(Model model) {
		model.addAttribute("season", new Season());
		model.addAttribute("listSeasons", this.seasonService.list());
		return "season";
	}

	// For add and update season both
	@RequestMapping(value = "/admin/season/add", method = RequestMethod.POST)
	public String addSeason(@ModelAttribute("season") Season p) {

		if (p.getId() == 0) {
			// new season, add it
			this.seasonService.add(p);
		} else {
			// existing season, call update
			this.seasonService.update(p);
		}

		return "redirect:/admin/seasons";

	}

	@RequestMapping("/admin/season/remove/{id}")
	public String removeSeason(@PathVariable("id") int id) {

		this.seasonService.remove(id);
		return "redirect:/admin/seasons";
	}

	@RequestMapping("/admin/season/edit/{id}")
	public String editSeason(@PathVariable("id") int id, Model model) {
		model.addAttribute("season", this.seasonService.getById(id));
		model.addAttribute("listSeasons", this.seasonService.list());
		return "season";
	}

	@RequestMapping(value = "/standings/{id}", method = RequestMethod.GET)
	public String standingsById(@PathVariable("id") int seasonId, Model model) {

		return standingsByIdInternal(seasonId, model);
	}

	
	@RequestMapping(value = "/standings", method = RequestMethod.GET)
	public String standings(Model model) {
		return standingsByIdInternal(seasonService.getCurrentSeason().getId(), model);
	}

	private String standingsByIdInternal(final int seasonId, Model model) {
		model.addAttribute("season", this.seasonService.getById(seasonId));
		model.addAttribute("listSeasons", this.seasonService.listStandingSeasons());
		final List<Standing> standings = this.seasonService.getStandings(seasonId);
		model.addAttribute("standings", standings);
		model.addAttribute("multiDivision", hasMultipleDivisions(standings));
		return "standing";
	}
	
	private boolean hasMultipleDivisions(final List<Standing> standings){
		boolean hasMultiDivision = false;
		if(!standings.isEmpty()){
			final Division firstDivision = standings.get(0).getCampaign().getDivision();
			for(final Standing standing : standings){
				if(!standing.getCampaign().getDivision().equals(firstDivision)){
					hasMultiDivision = true;
					break;
				}
			}
		}
		return hasMultiDivision;
	}
}