package com.infogain.app.service;

import java.util.List;

import com.infogain.app.entity.Product;

public interface SellerService {

	public Product saveProduct(Product prd);
	public Product updateProduct(Product prd, Long id);
	public String delteProduct(Long id);
	public List<Product> getProductDetail();
}
