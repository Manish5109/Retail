package com.infogain.app.service;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.infogain.app.entity.AppUser;
import com.infogain.app.entity.OrderDetail;
import com.infogain.app.exception.AuthenticationException;


@Service
public class HibernateServiceIMPL {
	
	@Bean
	public AppUser getUser() {
		return new AppUser();
	}
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private AppUser au;
	
	public AppUser saveUser(AppUser user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		trx = session.beginTransaction();
		session.save(user);
		trx.commit();
		session.close();
		return user;
	}
	
	public OrderDetail saveOrder(OrderDetail od) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			//AppUser ap = session.load(AppUser.class, au.getId());
			//od.setUserId(ap.getId()+"");
			//od.setUserName(ap.getUserName());
			session.save(od);
			trx.commit();
		}catch(Exception ex) {
			throw new AuthenticationException("getting excetion during save order",ex);
		}finally {
			session.close();
		}
		
		return od;
	}
	
	public OrderDetail saveOrderOrUpdate(OrderDetail ord) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			session.saveOrUpdate(ord);
			trx.commit();
		}catch(Exception ex) {
			throw new AuthenticationException("getting excetion during Update Order",ex);
		}finally {
			session.close();
		}
		
		return ord;
	}
	
	public Long deleteOrder(Long uid) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		 String hql = "delete from OrderDetail where id= :uid" ;
		try {
			trx = session.beginTransaction();
			Query query = session.createQuery(hql);
			trx.commit();
		}catch(Exception ex) {
			throw new AuthenticationException("getting error during delete order",ex);
		}finally {
			session.close();
		}
		
		return uid;
	}
	
	public List<OrderDetail> getOrderDetail() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction trx = null;
		List<OrderDetail> list =new ArrayList<OrderDetail>();
		try {
			trx = session.beginTransaction();
			 list = session.createQuery("from OrderDetail").list();
			 
			trx.commit();
		}catch(Exception ex) {
			throw new AuthenticationException("getting excetion during getting order",ex);
		}finally {
			session.close();
		}
		
		return list;
	}
}
