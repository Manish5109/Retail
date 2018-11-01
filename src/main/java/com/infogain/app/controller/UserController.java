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

import com.infogain.app.entity.Login;
import com.infogain.app.entity.UserDetail;
import com.infogain.app.exception.AuthenticationException;
import com.infogain.app.service.HibernateServiceIMPL;
import com.infogain.app.service.UserService;

@RestController
@RequestMapping(value = "/retail")
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
	@PostMapping(value="/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Login save(@RequestBody Login login) {
		return himpl.saveUser(login);
	}

	/**
	 * save user details by user.
	 * 
	 * @return
	 */
	 @PostMapping
	 @PreAuthorize("hasRole('ROLE_USER')")
	 public UserDetail save1(@RequestBody UserDetail user) {
	// return userService.saveUser(user);
		 return user;
	 }

	/**
	 * get user details.
	 * 
	 * @return
	 */
	//@RequestMapping(value = "/users/allUser", method = RequestMethod.POST)
	@GetMapping
	public List<Login> getAllUser() {
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
	public String deleteUser(@RequestBody Login user, HttpServletRequest request) {
		return userService.deleteUser(user);
	}
	
	/**
	 * save Or Update user details based on ID.
	 * 
	 * @return
	 */
	
	@PatchMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Login saveOrUpdateUser(@RequestBody Login user, HttpServletRequest request) {
		if(user.getId()==null) {
			throw new AuthenticationException("id could not be null", null);
		}
		return userService.saveOrUpdateUser(user);
	}	

}
