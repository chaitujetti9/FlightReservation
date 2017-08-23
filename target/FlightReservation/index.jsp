<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Flights</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>

	<%
		if (request.getAttribute("responseString") != null) {
	%>
	<h2>Welcome to Oasis Airlines Search:</h2>
	<%
		} else {
	%>
	<h2>
		<%
			request.getAttribute("responseString");
		%>
	</h2>
	<%
		}
	%>
	<form name="FlightSearch" method="post" action="search">
		<table>

			<tr>
				<td>From:</td>
				<td><input type="text" name="source" /></td>
			</tr>
			<tr>
				<td>To:</td>
				<td><input type="text" name="destination" /></td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><input type="date" name="date" /></td>
			</tr>
			<tr>
				<td>Tickets:</td>
				<td><input type="int" name="tickets" /></td>
			</tr>
		</table>
		<input type="submit" value="Search" />
	</form>

</body>
</html>