/*******************************************************************************
 * Copyright (c) 2018 Infogain.
 *******************************************************************************/
package com.infogain.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.app.entity.Login;
import com.infogain.app.repository.UserRepository;

/**
 * @author Manish Kumar
 * @since Sep 12, 2018
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infogain.test.service.UserService#saveUser(com.infogain.test.entity.User)
	 */
	@Override
	public Login saveUser(Login user) {
		LOGGER.debug("Saving user: {}", user);
		user = userRepository.save(user);
		user.setPassword("*****");
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.infogain.test.service.UserService#getAllUser()
	 */
	@Override
	public List<Login> getAllUser() {
		Iterator<Login> iterator = userRepository.findAll().iterator();
		List<Login> users = new ArrayList<>();
		while (iterator.hasNext()) {
			Login user = iterator.next();
			user.setPassword("*****");
			users.add(user);
		}
		return users;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infogain.test.service.UserService#deleteUser(com.infogain.test.entity.
	 * User)
	 */
	@Override
	public String deleteUser(Login user) {
		userRepository.delete(user);
		return "sucess";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infogain.test.service.UserService#saveOrUpdateUser(com.infogain.test.
	 * entity.User)
	 */

	@Override
	public Login saveOrUpdateUser(Login user) {

		user = userRepository.save(user);
		user.setPassword("#######");
		return user;

	}

}
