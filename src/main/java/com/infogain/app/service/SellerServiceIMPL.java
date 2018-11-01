package com.infogain.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.app.entity.OrderDetail;
import com.infogain.app.entity.Product;
import com.infogain.app.exception.AuthenticationException;

@Service
public class SellerServiceIMPL implements SellerService{

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Product saveProduct(Product prd) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
			Transaction trx = null;
			try {
				trx = session.beginTransaction();
				session.save(prd);
				trx.commit();
				
			}catch(Exception hex) {
				throw new AuthenticationException("seller save product",hex);
			}finally {
				session.close();
			}
		return prd;
	}

	@Override
	public Product updateProduct(Product prd, Long id) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		try {
			prd.setProductId(id);
			trx = session.beginTransaction();
			session.saveOrUpdate(prd);
			trx.commit();
			
		}catch(Exception hex) {
			throw new AuthenticationException("seller update product",hex);
		}finally {
			session.close();
		}
	return prd;
	}

	@Override
	public String delteProduct(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		try {
			
			trx = session.beginTransaction();
			Product prd = session.load(Product.class, id);
			session.delete(prd);
			trx.commit();
			
		}catch(Exception hex) {
			throw new AuthenticationException("seller delete product",hex);
		}finally {
			session.close();
		}
		return id+"deleted";
	}

	@Override
	public List<Product> getProductDetail() {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		List<Product> list =new ArrayList<Product>();
		try {
			trx = session.beginTransaction();
			 list = session.createQuery("from Product").list();
			 
			trx.commit();
		}catch(Exception ex) {
			throw new AuthenticationException("getting excetion during getting order",ex);
		}finally {
			session.close();
		}
		
		return list;
	}

}
