package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * PilotServiceImpl
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	/**
	 * Latihan 2 (delete Pilot)
	 */
	@Override
	public void deletePilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		pilotDb.delete(pilot);
	}
	
	/**
	 * Latihan 3 (update Pilot)
	 */
	public void updatePilot(String licenseNumber, String name, int flyHour) {
		pilotDb.updateName(name, licenseNumber);
		pilotDb.updateFlyHour(flyHour, licenseNumber); 
	}
}
