package com.jetti.rest;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jetti.flight.User;

public class ManageCard {

	private static SessionFactory factory;

	{
	try{
        factory = new Configuration().configure().buildSessionFactory();
     }catch (Throwable ex) { 
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex); 
     }
	}
	
	public Integer addCar(String cardNumber, String nameOnCard, int expiryDate, int cardBalance, int cvv) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer cardId = null;
		try {
			tx = session.beginTransaction();
			Card card = new Card(cardNumber, nameOnCard, expiryDate, cardBalance, cvv);
			cardId = (Integer) session.save(card);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cardId;
	}
	
	public boolean validateCard(String cardNumber, String nameOnCard, int expiryDate, int cvv,int price) {
		Session session = factory.openSession();
		Transaction tx = null;
//		List cards = null;
		boolean result=false;
		try {
			tx = session.beginTransaction();
//			String hql = "from card c where c.cardNumber= '" + cardNumber + "'c.nameOnCard = '" + nameOnCard +  "'c.cvv = '" + cvv + "'";
//			cards = session.createQuery(hql).list();
			
			Card card = (Card) session.get(Card.class, cardNumber);
			if(card.getNameOnCard().equals(nameOnCard)
					&&card.getExpiryDate()==expiryDate
					&&card.getCvv()==cvv)
			{
				if(card.getCardBalance()>=price)
				{
					result=true;
				}
				else
					result=false;
			}
			else
				result=false;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}	
		
		return result;
	}
}
