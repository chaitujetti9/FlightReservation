package com.jetti.flight;

import java.sql.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageReservation {

	private static SessionFactory factory;
	
	 {
	try{
        factory = new Configuration().configure().buildSessionFactory();
     }catch (Throwable ex) { 
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex); 
     }
	}
	
	public Integer addReservation(String username, int flightNo, int noOfTickets)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer reservationId = null;
		try {
			tx = session.beginTransaction();
			Reservation reservation = new Reservation(username, flightNo, noOfTickets,System.currentTimeMillis());
			reservationId = (Integer) session.save(reservation);
			Flight flight = (Flight) session.get(Flight.class, flightNo);
			int seats = flight.getSeatsAvailable();
			flight.setSeatsAvailable(seats-noOfTickets);
			session.update(flight);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
			{
				tx.rollback();
			}
				e.printStackTrace();
		} finally {
			session.close();
		}
		return reservationId;
	}
}
