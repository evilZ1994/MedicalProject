package com.example.mapper;

import com.example.bean.Patient;

public interface PatientMapper {
	
	Patient selectPatientById(int id);
	
	Void register(Patient patient);

}
