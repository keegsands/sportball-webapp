package org.keegsands.sportball.api;

import java.util.List;

import org.keegsands.sportball.model.Field;
import org.keegsands.sportball.model.GameTime;
import org.keegsands.sportball.service.FieldService;
import org.keegsands.sportball.service.GameTimeService;
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
	
	private static final String GET_GAME_TIMES = "/api/gameTimes";
	private static final String GET_GAME_TIME_BY_ID = "/api/gameTimes/{gameTimeId}";
	
	@Autowired
	private FieldService fieldService;
	
	@Autowired
	private GameTimeService gameTimeService;
	
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
	
	@RequestMapping(value=GET_GAME_TIMES, method = RequestMethod.GET)
	public ResponseEntity<List<GameTime>> getGameTimes() {
		return new ResponseEntity<>(gameTimeService.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_GAME_TIME_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<GameTime> getGameTimeById(@PathVariable int gameTimeId) {
		final GameTime gameTime = gameTimeService.getById(gameTimeId);
		return new ResponseEntity<>(gameTime, HttpStatus.OK);
	}
}
