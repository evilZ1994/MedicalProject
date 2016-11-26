package com.example.mapper;

import com.example.bean.Patient;

public interface PatientMapper {
	
	Patient selectPatientById(int id);
	
	void addPatient(Patient patient);

}
