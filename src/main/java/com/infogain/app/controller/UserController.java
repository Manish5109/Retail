/*******************************************************************************
 * Copyright (c) 2018 Infogain.
 *******************************************************************************/
package com.infogain.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.app.entity.AppUser;
import com.infogain.app.exception.AuthenticationException;
import com.infogain.app.service.HibernateServiceIMPL;
import com.infogain.app.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	HibernateServiceIMPL himpl;

	/**
	 * save user details by admin only.
	 * 
	 * @return
	 */
	//@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AppUser save(@RequestBody AppUser user, HttpServletRequest request) {
		return himpl.saveUser(user);
	}

	/**
	 * save user details by user.
	 * 
	 * @return
	 */
	// @RequestMapping(value = "/users/save1", method = RequestMethod.POST)
	// @PreAuthorize("hasRole('ROLE_USER')")
	// public AppUser save1(@RequestBody AppUser user, HttpServletRequest request) {
	// return userService.saveUser(user);
	// }

	/**
	 * get user details.
	 * 
	 * @return
	 */
	//@RequestMapping(value = "/users/allUser", method = RequestMethod.POST)
	@GetMapping
	public List<AppUser> getAllUser() {
		return userService.getAllUser();
	}
	/**
	 * delete user details based on ID.
	 * 
	 * @return
	 */

	//@RequestMapping(value = "/users/delete", method = RequestMethod.POST)
	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteUser(@RequestBody AppUser user, HttpServletRequest request) {
		return userService.deleteUser(user);
	}
	
	/**
	 * save Or Update user details based on ID.
	 * 
	 * @return
	 */
	
	@PatchMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AppUser saveOrUpdateUser(@RequestBody AppUser user, HttpServletRequest request) {
		if(user.getId()==null) {
			throw new AuthenticationException("id could not be null", null);
		}
		return userService.saveOrUpdateUser(user);
	}	

}
