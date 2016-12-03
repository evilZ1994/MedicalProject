package com.example.mapper;

import com.example.bean.Patient;

public interface PatientMapper {
	
	Patient selectPatientById(int id);
	
	Patient selectPatientByUsername(String username);
	
	Patient selectByUsernameAndPass(Patient patient);
	
	void addPatient(Patient patient);

	void addDoctor(int doctor_id, int patient_id);

}
