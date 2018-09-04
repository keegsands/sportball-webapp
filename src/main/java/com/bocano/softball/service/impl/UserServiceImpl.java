package com.bocano.softball.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bocano.softball.dao.UserDAO;
import com.bocano.softball.model.User;
import com.bocano.softball.service.UserService;

public class UserServiceImpl extends AbstractSimpleServiceImpl<User, UserDAO> implements UserService {

	public void setUserDAO(final UserDAO userDAO) {
		super.setDAO(userDAO);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return super.getDAO().findUserByUsername(username);
	}

}
