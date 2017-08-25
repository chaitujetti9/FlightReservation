<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Page</title>
</head>
<body>
<form name="payment" method= POST action="creditcardvalidate">
		<table>

			<tr>
				<td>Card Number:</td>
				<td><input type="text" name="cardNumber" /></td>
			</tr>
			<tr>
				<td>Name on Card:</td>
				<td><input type="text" name="nameOnCard" /></td>
			</tr>
			<tr>
				<td>Expiry Date:</td>
				<td><input type="text" name="expiryDate" value="MMYY" /></td>
			</tr>
			<tr>
				<td>CVV:</td>
				<td><input type="password" name="cvv" value="3-digit number" /></td>
			</tr>
		</table>
		<input type="submit" value="Reserve" />
	</form>
</body>
</html>