package com.example.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Doctor;
import com.example.bean.Patient;

public interface DoctorService {
	
	/**
	 * 根据用户名检查用户是否存在
	 */
	public Doctor getDoctorByUsername(String username);
	
	/**
	 * 通过用户名和密码查找用户
	 */
	public Doctor getByUsernameAndPass(String username, String password);
	
	/**
	 * 医生注册
	 * @throws JSONException 
	 */
	public JSONObject doctorRegister(JSONObject content) throws JSONException;
	
	/**
	 * 医生登录
	 */
	public JSONObject doctorLogin(JSONObject content);
}
