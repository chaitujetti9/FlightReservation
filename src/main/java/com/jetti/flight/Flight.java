package com.jetti.flight;

import java.sql.Date;

public class Flight {

	int flightNo;
	String source;
	String destination;
	int durationOfFlight;
	int timeOfFlight;
	Date dateOfFlight;
	int price;
	int seatsAvailable;

	public Flight(String source, String destination, int durationOfFlight, int timeOfFlight, Date dateOfFlight,
			int price, int seatsAvailable) {
		super();
		this.source = source;
		this.destination = destination;
		this.durationOfFlight = durationOfFlight;
		this.timeOfFlight = timeOfFlight;
		this.dateOfFlight = dateOfFlight;
		this.price = price;
		this.seatsAvailable = seatsAvailable;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDateOfFlight() {
		return dateOfFlight;
	}

	public void setDateOfFlight(Date dateOfFlight) {
		this.dateOfFlight = dateOfFlight;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDurationOfFlight() {
		return durationOfFlight;
	}

	public void setDurationOfFlight(int durationOfFlight) {
		this.durationOfFlight = durationOfFlight;
	}

	public int getTimeOfFlight() {
		return timeOfFlight;
	}

	public void setTimeOfFlight(int timeOfFlight) {
		this.timeOfFlight = timeOfFlight;
	}

}
