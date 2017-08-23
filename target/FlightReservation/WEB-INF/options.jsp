<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.jetti.flight.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Iterator" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
<h2>The following are the flights available:</h2>

<form action = "reserve", method = GET>
	
	<%	List availableFlights = (List) request.getSession().getAttribute("availableFlights");

		for (Iterator iterator = availableFlights.iterator(); iterator.hasNext();) {
		Flight flight = (Flight) iterator.next();
		FlightNo: flight.getFlightNo();
		Source: flight.getSource();
		Destination: flight.getDestination();
		Time: flight.getTimeOfFlight();
		Duration: flight.getDurationOfFlight();
		Date: flight.getDateOfFlight();
	} %>
	
	
	Enter the flight id:
	<input type = "text" name = "flightNo"> 
	<input type = "Submit" value = "Reserve">
	<a href= "index">Edit Search Details</a>

</form>
</body>
</html>