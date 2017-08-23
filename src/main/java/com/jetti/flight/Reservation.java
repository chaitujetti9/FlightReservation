package com.jetti.flight;

public class Reservation {

	int reservationId;
	long time;
	String username;
	int flightNO;
	int noOfTickets;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getFlightNO() {
		return flightNO;
	}

	public void setFlightNO(int flightNO) {
		this.flightNO = flightNO;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public Reservation(String username, int flightNO, int noOfTickets) {
		super();
		this.username = username;
		this.flightNO = flightNO;
		this.noOfTickets = noOfTickets;
	}

}
