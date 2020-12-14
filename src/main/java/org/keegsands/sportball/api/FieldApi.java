package org.keegsands.sportball.api;

import java.util.List;

import org.keegsands.sportball.model.Field;
import org.keegsands.sportball.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldApi {
	
	private static final String GET_FIELDS = "/api/fields";
	private static final String GET_FIELD_BY_ID = "/api/fields/{fieldId}";
	
	@Autowired
	private FieldService fieldService;
	
	public FieldApi() {
		System.out.println("Starting the field API");
	}

	@RequestMapping(value=GET_FIELDS, method = RequestMethod.GET)
	public ResponseEntity<List<Field>> getAllFields() {
		return new ResponseEntity<>(fieldService.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_FIELD_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Field> getFieldById(@PathVariable int fieldId) {
		final Field field = fieldService.getById(fieldId);
		return new ResponseEntity<>(field, HttpStatus.OK);
	}
}
