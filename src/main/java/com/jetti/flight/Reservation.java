package com.jetti.flight;

public class Reservation {

	int reservationId;
	long time;
	String username;
	int flightNo;
	int noOfTickets;
	

	public Reservation() {
	}

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

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public Reservation(String username, int flightNO, int noOfTickets,long time) {
		super();
		this.username = username;
		this.flightNo = flightNO;
		this.noOfTickets = noOfTickets;
		this.time = time;
	}

}
