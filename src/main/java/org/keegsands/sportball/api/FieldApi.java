package org.keegsands.sportball.api;

import java.util.ArrayList;
import java.util.List;

import org.keegsands.sportball.model.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fields")
public class FieldApi {
	
	public FieldApi() {
		System.out.println("starting controller");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Field>> getAllFields() {
		final List<Field> fields = new ArrayList<>();
		final Field field = new Field();
		field.setName("Pease");
		field.setId(1);
		fields.add(field);
		return new ResponseEntity<>(fields, HttpStatus.OK);
	}
}
