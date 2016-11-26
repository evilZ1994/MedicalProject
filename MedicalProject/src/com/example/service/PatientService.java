package com.example.service;

import com.example.bean.Patient;

public interface PatientService {
	
	/**
	 * 患者注册
	 * @param patient
	 * @return
	 */
	public boolean patientRegister(Patient patient);
}
