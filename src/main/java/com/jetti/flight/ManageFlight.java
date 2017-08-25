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

public class ManageFlight {

	private static SessionFactory factory;

	 {
	try{
        factory = new Configuration().configure().buildSessionFactory();
     }catch (Throwable ex) { 
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex); 
     }
	}
	
	public Integer addFlight(String source, String destination, int durationOfFlight, int timeOfFlight,
			Date dateOfFlight,int price,int seatsAvailable) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer flightNo = null;
		try {
			tx = session.beginTransaction();
			Flight flight = new Flight(source, destination, durationOfFlight, timeOfFlight, dateOfFlight,price,seatsAvailable);
			flightNo = (Integer) session.save(flight);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flightNo;
	}

	/* Method to READ all the flights */
	public List listFlights(String source, String destination) {
		Session session = factory.openSession();
		Transaction tx = null;
		List flights = null;
//		Flight f = new Flight();
		try {
			tx = session.beginTransaction();
			String hql = "from Flight f where f.source = '"+source+"' and f.destination = '"+destination+"'";
			flights = session.createQuery(hql).list();
			for (Iterator iterator = flights.iterator(); iterator.hasNext();) {
				Flight flight = (Flight) iterator.next();
				System.out.print(" Flight No: " + flight.getFlightNo());
				System.out.print(" Source: " + flight.getSource());
				System.out.print(" Destination: " + flight.getDestination());
				System.out.print(" Time: " + flight.getTimeOfFlight());
				System.out.print(" Duration: " + flight.getDurationOfFlight());
				System.out.print(" Date: " + flight.getDateOfFlight());
				System.out.println(" Price: " + flight.getPrice());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return flights;
	}
	
	public List searchFlights(String source, String destination, Date dateOfFlight,int tickets) {
		Session session = factory.openSession();
		Transaction tx = null;
		List flights = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Flight f where f.source = '"+source+"' and f.destination = '"+destination+"' and f.seatsAvailable >= "+tickets;
			flights = session.createQuery(hql).list();
			if(flights.isEmpty())
			{
				System.out.println("No flights retrieved");
			}
			for (Iterator iterator = flights.iterator(); iterator.hasNext();) {
				Flight flight = (Flight) iterator.next();
				System.out.print(" Flight No: " + flight.getFlightNo());
				System.out.print(" Source: " + flight.getSource());
				System.out.print(" Destination: " + flight.getDestination());
				System.out.print(" Time: " + flight.getTimeOfFlight());
				System.out.print(" Duration: " + flight.getDurationOfFlight());
				System.out.print(" Date: " + flight.getDateOfFlight());
				System.out.println(" Price: "+ flight.getPrice());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return flights;
	}

	/* Method to UPDATE seats of a flight */
	public void updateSeats(Integer flightNo, int seatsBooked) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Flight flight = (Flight) session.get(Flight.class, flightNo);
			int seats = flight.getSeatsAvailable();
			flight.setSeatsAvailable(seats-seatsBooked);
			session.update(flight);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE a flight from the records */
	public void deleteFlight(Integer flightNo) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Flight flight = (Flight) session.get(Flight.class, flightNo);
			session.delete(flight);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
