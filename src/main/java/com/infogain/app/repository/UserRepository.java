package com.infogain.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.infogain.app.entity.Login;

public interface UserRepository extends CrudRepository<Login, Long> {

	public Login findByUserName(String username);

}
