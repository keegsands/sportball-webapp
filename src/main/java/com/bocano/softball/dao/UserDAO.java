package com.bocano.softball.dao;

import com.bocano.softball.model.User;

public interface UserDAO extends SimpleDAO<User> {
	User findUserByUsername(final String username);
}
