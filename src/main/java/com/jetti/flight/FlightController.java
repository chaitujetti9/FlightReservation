package com.jetti.flight;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value= {"/"})
public class FlightController {

	@RequestMapping(value="search",method = RequestMethod.POST)
	public ModelAndView search(@RequestParam  String source,
								@RequestParam String destination,
								@RequestParam ("date") Date dateOfFlight,
								@RequestParam int tickets,HttpServletRequest request)
	{

		ModelAndView mvObj = new ModelAndView();
		ManageFlight mfObj = new ManageFlight();
		List availableFlights = null;
		System.out.println("Success");
		if(dateOfFlight!=null)
		{
			availableFlights = mfObj.searchFlights(source, destination, (java.sql.Date) dateOfFlight,tickets);
			if(availableFlights.isEmpty())
			{
				mvObj.setViewName("index");
				mvObj.addObject("responseString","No flights available");
			}
			else
			{
			mvObj.setViewName("options");
			mvObj.addObject("availableFlights",availableFlights);
			request.getSession().setAttribute("tickets", tickets);
			}
		}
		else
		{
			availableFlights = mfObj.listFlights(source, destination);
			if(availableFlights.isEmpty())
			{
				mvObj.setViewName("index");
				mvObj.addObject("responseString","No flights available");
			}
			else
			{
			mvObj.setViewName("options");
			mvObj.addObject(availableFlights);
			request.getSession().setAttribute("tickets", tickets);			
			}
		}
		
		return mvObj;
	}
	
	@RequestMapping(value = "reserve", method = RequestMethod.POST)
	public ModelAndView reserve(HttpServletRequest request)
	{
		ModelAndView mvObj = new ModelAndView();
//		m.addAttribute("flightNo",flightNo);
		String flightNo = (String) request.getAttribute("flightNo");
		request.getSession().setAttribute("flightNo", flightNo );
		mvObj.setViewName("login");
		return mvObj;
	}
	
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public ModelAndView validate(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mvObj = new ModelAndView();
		ManageUser muObj = new ManageUser();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(muObj.validateUser(username, password))
		{
			mvObj.setViewName("confirm");
			request.getSession().setAttribute("username", username);
		}
		
		else
		{
			mvObj.setViewName("login");
			mvObj.addObject("responseString","Invalid Login Credentials");
		}
		
		return mvObj;
	}
	
	@RequestMapping(value="confirmed", method = RequestMethod.POST)
	public ModelAndView confirmed(HttpSession session)
	{
		ModelAndView mvObj = new ModelAndView();
		ManageReservation mrObj = new ManageReservation();
		
		String username = (String) session.getAttribute("username");
		int flightNo = (Integer) session.getAttribute("flightNo");
		int noOfTickets = (Integer) session.getAttribute("tickets");
		
		mrObj.addReservation(username, flightNo, noOfTickets);
		
		mvObj.setViewName("confirmation");
		mvObj.addObject("responseString","Reservation Confirmed");
		
		return mvObj;
		
	}
	
}
