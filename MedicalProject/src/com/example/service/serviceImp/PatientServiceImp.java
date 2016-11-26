package com.example.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Patient;
import com.example.mapper.PatientMapper;
import com.example.service.PatientService;

@Service
public class PatientServiceImp implements PatientService {

	@Autowired
	private PatientMapper patientMapper;

	public boolean patientRegister(Patient patient) {
		
		patientMapper.addPatient(patient);
		
		return true;
	}

	

}
