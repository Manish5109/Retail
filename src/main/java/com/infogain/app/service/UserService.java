/*******************************************************************************
 * Copyright (c) 2018 Infogain.
 *******************************************************************************/
package com.infogain.app.service;

import java.util.List;

import com.infogain.app.entity.AppUser;

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
	public AppUser saveUser(AppUser user);

	/**
	 * Get all users.
	 * 
	 * @return
	 */
	public List<AppUser> getAllUser();

	/**
	 * delte user details by Id.
	 * 
	 * @param id
	 * @return
	 */
	public String deleteUser(AppUser user);

	
	/**
	 * save or Update user details by Id.
	 * 
	 * @param id
	 * @return
	 */

	public AppUser saveOrUpdateUser(AppUser user);
}
