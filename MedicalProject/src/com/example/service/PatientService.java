package com.example.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Patient;

public interface PatientService {
	
	/**
	 * 根据用户名检查用户是否存在
	 */
	public Patient getPatientByUsername(String username);
	
	/**
	 * 患者注册
	 * @param patient
	 * @return
	 * @throws JSONException 
	 */
	public JSONObject patientRegister(JSONObject content) throws JSONException;
}
