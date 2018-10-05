package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDB;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	/**
	 *  Latihan 2 (delete Flight)
	 */
	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
	}

	@Override
	public FlightModel getFlightById(long id) {
		// TODO Auto-generated method stub
		return flightDb.findById(id);
	}

	/**
	 * Latihan 3 (update Flight)
	 */
	@Override
	public void updateFlight(long id, String destination, String flightNumber, String origin, Date time) {
		// TODO Auto-generated method stub
		flightDb.updateDestination(destination, id);
		flightDb.updateFlightNumber(flightNumber, id);
		flightDb.updateOrigin(origin, id);
		flightDb.updateTime(time, id);
	}

	@Override
	public List<FlightModel> viewAll() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}
	

}
