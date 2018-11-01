package com.infogain.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.app.entity.Product;
import com.infogain.app.entity.Response;
import com.infogain.app.service.SellerServiceIMPL;

@RestController
@RequestMapping(value="/products")
public class SellerController {
	
	@Autowired
	SellerServiceIMPL service;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Product> uploadProduct(@RequestBody Product prd) {
		service.saveProduct(prd);
		return new ResponseEntity<Product>(service.saveProduct(prd), HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product prd, @PathVariable ("id") Long id) {
		return new ResponseEntity<Product>(service.updateProduct(prd,id), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response> deleteProduct(@PathVariable ("id") Long id) {
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "product has been deleted"), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct() {
		return new ResponseEntity<List<Product>>(service.getProductDetail(), HttpStatus.OK);
	}
}
