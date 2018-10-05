package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FlightController
 * @author hauri
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFligthSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	/**
	 *  Latihan 2 (delete Flight)
	 */
	@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "id") long id) {
		FlightModel flight = flightService.getFlightById(id);
		flight.getPilot().getPilotFlight().remove(flight);
		flightService.deleteFlight(flight);
		return "delete";
	}
	
	/**
	 * Latihan 3 (update Flight)
	 */
	@RequestMapping(value = "flight/update/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "id") long id, Model model){
		FlightModel flight = flightService.getFlightById(id);
		PilotModel pilot = flight.getPilot();
		
		FlightModel newflight = new FlightModel();
		newflight.setId(id);
		newflight.setPilot(pilot);
		
		model.addAttribute("newFlight", newflight);
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updateSubmit(@ModelAttribute FlightModel newFlight) {
		long id = newFlight.getId();
		String destination = newFlight.getDestination();
		String flightNumber = newFlight.getFlightNumber();
		String origin = newFlight.getOrigin();
		Date time = newFlight.getTime();
		
		flightService.updateFlight(id, destination, flightNumber, origin, time);
		
		return "update";
	}
	
	/**
	 * Latihan 4
	 */
	@RequestMapping(value = "/flight/viewall")
	private String viewAll(Model model) {
		model.addAttribute("flightList", flightService.viewAll());
		return "viewAll";
	}
}
