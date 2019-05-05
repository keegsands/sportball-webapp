package org.keegsands.sportball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.keegsands.sportball.model.Field;
import org.keegsands.sportball.service.FieldService;

@Controller
public class FieldController {
	private FieldService fieldService;

	/**
	 * Simple setter for the FieldService
	 * 
	 * @param ps
	 *            FieldService
	 */
	@Autowired(required = true)
	@Qualifier(value = "fieldService")
	public void setFieldService(FieldService ps) {
		this.fieldService = ps;
	}

	/**
	 * List all the fields and also include a new field.
	 * 
	 * @param model
	 *            Model
	 * @return field page
	 */
	@RequestMapping(value = "/admin/fields", method = RequestMethod.GET)
	public String listFields(Model model) {
		model.addAttribute("field", new Field());
		model.addAttribute("listFields", this.fieldService.list());
		return "field";
	}

	// For add and update field both
	@RequestMapping(value = "/admin/field/add", method = RequestMethod.POST)
	public String addField(@ModelAttribute("field") Field p) {

		if (p.getId() == 0) {
			// new field, add it
			this.fieldService.add(p);
		} else {
			// existing field, call update
			this.fieldService.update(p);
		}

		return "redirect:/admin/fields";

	}

	@RequestMapping("/admin/field/remove/{id}")
	public String removeField(@PathVariable("id") int id) {

		this.fieldService.remove(id);
		return "redirect:/admin/fields";
	}

	@RequestMapping("/admin/field/edit/{id}")
	public String editField(@PathVariable("id") int id, Model model) {
		model.addAttribute("field", this.fieldService.getById(id));
		model.addAttribute("listFields", this.fieldService.list());
		return "field";
	}
}
