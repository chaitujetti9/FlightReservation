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

<form action = "reserve", method = POST>
	
	<table>
	<%	List availableFlights = (List) request.getAttribute("availableFlights");
		
		for (Iterator iterator = availableFlights.iterator(); iterator.hasNext();) {
		Flight flight = (Flight) iterator.next();%>
		<tr>
		
		<td><%= "FlightNo:"+flight.getFlightNo()+" " %>  </td>
		<td><%= "Source:"+flight.getSource()+" " %> </td>
		<td><%= "Destination:"+flight.getDestination()+" " %> </td>
		<td><%= "Time:"+flight.getTimeOfFlight() +" "%> </td>
		<td><%= "Duration:"+flight.getDurationOfFlight()+" " %> </td>
		<td><%= "Date:"+flight.getDateOfFlight() +" "%> </td>
		<td><%= "Price:"+flight.getPrice()+" " %> </td>
		
		</tr>
	<% } %>
		
	</table>
	
	
	Enter the flight id:
	<input type = "int" name = "flightNo"> 
	<input type = "Submit" value = "Reserve">
	<a href= "http://localhost:8080/FlightReservation/index.jsp">Edit Search Details</a>

</form>
</body>
</html>