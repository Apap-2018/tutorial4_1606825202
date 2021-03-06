package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

import java.awt.print.Printable;

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
 * PilotController
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	/**
	 * View Pilot berdasarkan License Number
	 */
	@RequestMapping("/pilot/view")
	private String viewPilotByLicenseNumber(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilot != null) {
			model.addAttribute("pilot", pilot);
			return "view-pilot";	
		}
		else {
			model.addAttribute("licenseNumber", licenseNumber);
			return "errorNoPilot";
		}
	}
	
	/**
	 * Latihan 1 
	 */
	@RequestMapping(value = "/pilot/view-flights/{licenseNumber}", method = RequestMethod.GET)
	private String viewPilotFlightsByLicenseNumber(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilot!=null) {
			model.addAttribute("pilotName", pilot.getName());
			model.addAttribute("flightList", pilot.getPilotFlight());
			model.addAttribute("pilot", pilot);
			return "view-flights";			
		}
		else {
			model.addAttribute("licenseNumber", licenseNumber);
			return "errorNoPilot";
		}
	}
	
	/**
	 * Latihan 2 (menghapus Pilot)
	 */
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String deletePilotByLicenseNumber(@PathVariable(value = "licenseNumber") String licenseNumber) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilotService.deletePilot(pilot);
		return "delete";
	}
	

	/**
	 * Latihan 3 (meng-update Pilot)
	 */
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {		
		PilotModel newPilot = new PilotModel();
		newPilot.setLicenseNumber(licenseNumber);
		
		model.addAttribute("newPilot", newPilot);
		return "updatePilot";
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	private String updateSubmit(@ModelAttribute PilotModel newPilot, Model model) {
		String licenseNumber = newPilot.getLicenseNumber();
		String name = newPilot.getName();
		int flyHour = newPilot.getFlyHour();
		
		pilotService.updatePilot(licenseNumber, name, flyHour);
		return "update";
	}

}
