package com.infogain.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.app.entity.OrderDetail;
import com.infogain.app.service.HibernateServiceIMPL;

@RestController
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	HibernateServiceIMPL himpl;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public OrderDetail save(@RequestBody OrderDetail ordDetail) {
		himpl.saveOrder(ordDetail);
		return ordDetail;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public List<OrderDetail> getDetail() {
		return himpl.getOrderDetail();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/{id}")
	public OrderDetail update(@RequestBody OrderDetail ord) {
		return himpl.saveOrderOrUpdate(ord);
	}
	
	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/delete/{id}")
	public String deleteRecords(@PathVariable("id") Long id) {
		return himpl.deleteOrder(id)+"has been deleted";
	}
	
}
