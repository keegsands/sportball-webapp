package com.bocano.softball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bocano.softball.model.GameTime;
import com.bocano.softball.service.GameTimeService;

@Controller
public class GameTimeController {
	private GameTimeService gameTimeService;

	@Autowired(required = true)
	@Qualifier(value = "gameTimeService")
	public void setGameTimeService(GameTimeService ps) {
		this.gameTimeService = ps;
	}

	@RequestMapping(value = "/admin/gameTimes", method = RequestMethod.GET)
	public String listGameTimes(Model model) {
		model.addAttribute("gameTime", new GameTime());
		model.addAttribute("listGameTimes", this.gameTimeService.list());
		return "gameTime";
	}

	// For add and update gameTime both
	@RequestMapping(value = "/admin/gameTime/add", method = RequestMethod.POST)
	public String addGameTime(@ModelAttribute("gameTime") GameTime p) {

		if (p.getId() == 0) {
			// new gameTime, add it
			this.gameTimeService.add(p);
		} else {
			// existing gameTime, call update
			this.gameTimeService.update(p);
		}

		return "redirect:/admin/gameTimes";

	}

	@RequestMapping("/admin/gameTime/remove/{id}")
	public String removeGameTime(@PathVariable("id") int id) {

		this.gameTimeService.remove(id);
		return "redirect:/admin/gameTimes";
	}

	@RequestMapping("/admin/gameTime/edit/{id}")
	public String editGameTime(@PathVariable("id") int id, Model model) {
		model.addAttribute("gameTime", this.gameTimeService.getById(id));
		model.addAttribute("listGameTimes", this.gameTimeService.list());
		return "gameTime";
	}
}