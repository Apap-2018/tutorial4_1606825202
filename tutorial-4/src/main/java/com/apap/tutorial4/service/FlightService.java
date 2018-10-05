package com.apap.tutorial4.service;

import java.sql.Date;
import java.util.List;

import com.apap.tutorial4.model.FlightModel;

/**
 * FlightService
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	
	/**
	 * Latihan 2 (delete Flight)
	 */
	FlightModel getFlightById(long id);
	void deleteFlight(FlightModel flight);
	
	/**
	 * Latihan 3 (update Flight)
	 */
	void updateFlight(long id, String destination, String flightNumber, String origin, Date time);
	
	/**
	 * Latihan 4
	 * @return 
	 */
	List<FlightModel> viewAll();
}
