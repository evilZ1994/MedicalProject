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
	 * 通过用户名和密码查找用户
	 */
	public Patient getByUsernameAndPass(String username, String password);
	
	/**
	 * 患者注册
	 * @throws JSONException 
	 */
	public JSONObject patientRegister(JSONObject content) throws JSONException;
	
	/**
	 * 患者登录
	 */
	public JSONObject patientLogin(JSONObject content);

	/**
	 * 添加医生
	 */
	public boolean addDoctor(int doctorId, int patientId);
}
