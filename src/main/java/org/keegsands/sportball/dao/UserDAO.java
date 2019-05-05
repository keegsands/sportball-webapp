package org.keegsands.sportball.dao;

import org.keegsands.sportball.model.User;

public interface UserDAO extends SimpleDAO<User> {
	User findUserByUsername(final String username);
}
