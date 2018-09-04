package com.bocano.softball.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bocano.softball.dao.UserDAO;
import com.bocano.softball.model.User;

public class UserDAOInMemoryImpl implements UserDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOInMemoryImpl.class);
	private static final String ADMIN_PWD = "$2a$10$ARk3ttuBeLg1xEtB9uRlWuV8P4xSIywd.A5Tu8XmmOxMJ8VRRfaii";
	private static final String CAPT_PWD = "$2a$10$k1L5owWXH1i8kyA5Lv4Iu.vVYY/4D5MM0jsk2cazpYO/MFbNHA2S.";

	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";

	private static final Map<String, User> USERS = createUsersMap();

	private static Map<String, User> createUsersMap() {
		final Map<String, User> userMap = new HashMap<String, User>();
		if (userMap.isEmpty()) {
			userMap.put("ksands", new User("ksands", ADMIN_PWD, ROLE_ADMIN + "," + ROLE_USER, "ksands@loftware.com", true));
			userMap.put("commissioner", new User("commissioner", CAPT_PWD, ROLE_ADMIN + "," + ROLE_USER, "Jacob.Wojtkowski@teledyne.com", true));
			userMap.put("jplace", new User("jplace", CAPT_PWD, ROLE_USER, "jerre@bayring.com", true));
			userMap.put("mcuneo", new User("mcuneo", CAPT_PWD, ROLE_USER, "MCuneo@bottomline.com", true));
			userMap.put("smalavenda", new User("smalavenda", CAPT_PWD, ROLE_USER, "shane.malavenda@lonza.com", true));
			userMap.put("kmavrogeorge", new User("kmavrogeorge", CAPT_PWD, ROLE_USER, "KAMavrogeorge@tighebond.com", true));
			userMap.put("mallen", new User("mallen", CAPT_PWD, ROLE_USER, "allenmd@westinghouse.com", true));
			userMap.put("jtalbot", new User("jtalbot", CAPT_PWD, ROLE_USER, "jtalbot@jhancock.com", true));
			userMap.put("pmartin", new User("pmartin", CAPT_PWD, ROLE_USER, "Paul.Martin@ca.com", true));
			userMap.put("cfrazier", new User("cfrazier", CAPT_PWD, ROLE_USER, "FrazierCE@state.gov", true));
			userMap.put("tcullity", new User("tcullity", CAPT_PWD, ROLE_USER, "timcullity@hotmail.com", true));
			userMap.put("mmckoy", new User("mmckoy", CAPT_PWD, ROLE_USER, "matthew.mckoy@medtronic.com", true));
			userMap.put("tdee", new User("tdee", CAPT_PWD, ROLE_USER, "Tim.Dee@craftbrew.com", true));
			userMap.put("fhanson", new User("fhanson", CAPT_PWD, ROLE_USER, "Frank.Hanson@Teledyne.com", true));
			userMap.put("shinman", new User("shinman", CAPT_PWD, ROLE_USER, "SHinman@pcconnection.com", true));
			userMap.put("jbasham", new User("jbasham", CAPT_PWD, ROLE_USER, "jbasham@seacoastonline.com", true));
			userMap.put("bdesmond", new User("bdesmond", CAPT_PWD, ROLE_USER, "bdesmond@ridecj.com", true));
			userMap.put("mfischer", new User("mfischer", CAPT_PWD, ROLE_USER, "mfischer@ccsnh.edu", true));
		}
		return userMap;
	}

	@Override
	public User findUserByUsername(String username) {
		LOGGER.info("Looking up user information for: " + username);
		return USERS.get(username.toLowerCase());
	}

	@Override
	public void add(User p) {
		
	}

	@Override
	public void update(User p) {
		
	}

	@Override
	public List<User> list() {
		
		return null;
	}

	@Override
	public User getById(int id) {
		
		return null;
	}

	@Override
	public void remove(int id) {
		

	}

}
