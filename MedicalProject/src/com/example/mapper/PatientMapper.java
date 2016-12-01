package com.example.mapper;

import com.example.bean.Patient;

public interface PatientMapper {
	
	Patient selectPatientById(int id);
	
	Patient selectPatientByUsername(String username);
	
	void addPatient(Patient patient);

}
