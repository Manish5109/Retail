/*******************************************************************************
 * Copyright (c) 2018 Infogain.
 *******************************************************************************/
package com.infogain.app.service;

import java.util.List;

import com.infogain.app.entity.Login;

/**
 * @author Manish Kumar
 * @since Sep 12, 2018
 */
public interface UserService {

	/**
	 * Save user details and return user id.
	 * 
	 * @param user
	 * @return
	 */
	public Login saveUser(Login user);

	/**
	 * Get all users.
	 * 
	 * @return
	 */
	public List<Login> getAllUser();

	/**
	 * delte user details by Id.
	 * 
	 * @param id
	 * @return
	 */
	public String deleteUser(Login user);

	
	/**
	 * save or Update user details by Id.
	 * 
	 * @param id
	 * @return
	 */

	public Login saveOrUpdateUser(Login user);
}
