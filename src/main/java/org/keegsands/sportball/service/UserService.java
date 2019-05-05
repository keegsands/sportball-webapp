package org.keegsands.sportball.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.keegsands.sportball.model.User;

public interface UserService extends SimpleService<User>, UserDetailsService {

}
