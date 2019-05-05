package org.keegsands.sportball.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.keegsands.sportball.dao.UserDAO;
import org.keegsands.sportball.model.User;
import org.keegsands.sportball.service.UserService;

public class UserServiceImpl extends AbstractSimpleServiceImpl<User, UserDAO> implements UserService {

	public void setUserDAO(final UserDAO userDAO) {
		super.setDAO(userDAO);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return super.getDAO().findUserByUsername(username);
	}

}
