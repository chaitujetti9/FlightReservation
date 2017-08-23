package com.jetti.flight;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageUser {

	private static SessionFactory factory;
	
	{
		try{
	        factory = new Configuration().configure().buildSessionFactory();
	     }catch (Throwable ex) { 
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
	     }
		}

	public Integer addUser(String firstName, String lastName, String email, String username, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer userId = null;
		try {
			tx = session.beginTransaction();
			User user = new User(firstName, lastName, email, username, password);
			userId = (Integer) session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userId;
	}

	/* Method to validate the user */
	public boolean validateUser(String username, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		List users = null;
		try {
			tx = session.beginTransaction();
			String hql = "from user u where u.username = '" + username + "'u.password = '" + password + "'";
			users = session.createQuery(hql).list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (users.isEmpty()) {
			return true;

		} else {
			return false;
		}

	}

}
