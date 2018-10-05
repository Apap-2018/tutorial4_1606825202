package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

/**
 * PilotService
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	
	void addPilot(PilotModel pilot);
	
	// Latihan 2 (menghapus Pilot)
	void deletePilot(PilotModel pilot);
	
	//Latihan 3 (meng-update Pilot)
	void updatePilot(String licenseNumber, String name, int flyHour);
}
