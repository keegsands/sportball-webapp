package org.keegsands.sportball.api;

import java.util.List;

import org.keegsands.sportball.model.Field;
import org.keegsands.sportball.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fields")
public class FieldApi {
	
	@Autowired
	private FieldService fieldService;
	
	public FieldApi() {
		System.out.println("Starting the field API");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Field>> getAllFields() {
		return new ResponseEntity<>(fieldService.list(), HttpStatus.OK);
	}
}
