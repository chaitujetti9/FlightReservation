package com.jetti.flight;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value= {"/"})
public class FlightController {
	
	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/CardMicroService/service";
	private static final String SUCCESS_RESULT="<response>SUCCESS</response>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";

	@RequestMapping(value="search",method = RequestMethod.POST)
	public ModelAndView search(@RequestParam  String source,
								@RequestParam String destination,
								//@RequestParam ("date") Date dateOfFlight,
								@RequestParam int tickets,HttpServletRequest request,HttpSession session)
	{

		ModelAndView mvObj = new ModelAndView();
		ManageFlight mfObj = new ManageFlight();
		Date dateOfFlight = (Date) request.getAttribute("date");
		List availableFlights = null;
		System.out.println("Success");
//		if(dateOfFlight!=null)
//		{
			availableFlights = mfObj.searchFlights(source, destination, (java.sql.Date) dateOfFlight,tickets);
			if(availableFlights.isEmpty())
			{
				mvObj.addObject("responseString","No flights available");
				mvObj.setViewName("index");
				
			}
			else
			{
			mvObj.setViewName("options");
			mvObj.addObject("availableFlights",availableFlights);
			session.setAttribute("tickets", tickets);
			}
//		}
//		else
//		{
//			availableFlights = mfObj.listFlights(source,destination);
//			if(availableFlights.isEmpty())
//			{
//				mvObj.setViewName("index");
//				mvObj.addObject("responseString","No flights available");
//			}
//			else
//			{
//			mvObj.setViewName("options");
//			mvObj.addObject(availableFlights);
//			request.getSession().setAttribute("tickets", tickets);			
//			}
//		}
		
		return mvObj;
	}
	
	@RequestMapping(value = "reserve", method = RequestMethod.POST)
	public ModelAndView reserve(@RequestParam String flightNo,HttpSession session)
	{
		ModelAndView mvObj = new ModelAndView();
//		m.addAttribute("flightNo",flightNo);
//		String flightNo = (String) request.getAttribute("flightNo");
		System.out.println("line 73 "+flightNo);
		session.setAttribute("flightNo", flightNo);
		System.out.println("Line 75 "+session.getAttribute("flightNo"));
		mvObj.setViewName("login");
		return mvObj;
	}
	
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public ModelAndView validate(HttpServletRequest request, HttpSession session)
	{
		ModelAndView mvObj = new ModelAndView();
		ManageUser muObj = new ManageUser();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean result= muObj.validateUser(username, password);
//		if(result==true)
//		{
			mvObj.setViewName("payment");
			session.setAttribute("username", username);
//		}		
//		else
//		{
//			mvObj.setViewName("login");
//			request.setAttribute("responseString","Invalid Login Credentials");
//		}
		
		return mvObj;
	}
	
	@RequestMapping(value="creditcardvalidate",method= RequestMethod.POST)
	public ModelAndView creditCardValidate(HttpSession session,HttpServletRequest request)
	{
		ModelAndView mvObj = new ModelAndView();
		
		 Form form = new Form();
	      form.param("cardNumber",(String) request.getParameter("cardNumber"));
	      form.param("nameOnCard",(String) request.getParameter("nameOnCard"));
	      form.param("expiryDate",(String) request.getParameter("expiryDate"));
	      form.param("cvv",(String) request.getParameter("cvv"));
	      form.param("price","3000");
	      
	      client = ClientBuilder.newClient();
	      String callResult = client
	         .target("http://localhost:8080/CardMicroService/service")
	         .path("/validatecard")
	         .request(MediaType.APPLICATION_XML)
	         .put(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),String.class);
	      String result = PASS;
	      System.out.println(callResult);
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	         mvObj.setViewName("payment");
	      }
	      else
	      {
	    	  result = PASS;
	    	  mvObj.setViewName("confirm");
	      }

	      System.out.println("Payment Result: " + result );
	      
	      return mvObj;
	   }
		
	
	@RequestMapping(value="confirmed", method = RequestMethod.POST)
	public ModelAndView confirmed(HttpSession session)
	{
		ModelAndView mvObj = new ModelAndView();
		ManageReservation mrObj = new ManageReservation();
		ManageFlight mfObj = new ManageFlight();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		String x = (String) session.getAttribute("flightNo");
		int flightNo = Integer.parseInt(x);
		System.out.println("Line 156 "+flightNo);
		int noOfTickets = (Integer) session.getAttribute("tickets");
		System.out.println("Line 158 "+noOfTickets);
		
		mrObj.addReservation(username, flightNo, noOfTickets);
		mfObj.updateSeats(flightNo,noOfTickets);
		
		mvObj.setViewName("confirmation");
		mvObj.addObject("responseString","Reservation Confirmed");
		
		return mvObj;
		
	}
	
}
