package com.bocano.softball.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bocano.softball.model.User;

public interface UserService extends SimpleService<User>, UserDetailsService {

}
