package com.example.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Doctor;
import com.example.bean.Patient;

public interface PatientService {
	/**
	 * 根据用户id查找用户
	 */
	public Patient getPatientById(int id);
	
	/**
	 * 根据用户名检查用户是否存在
	 */
	public Patient getPatientByUsername(String username);
	
	/**
	 * 保证JMessage上用户名唯一性，患者和医生用户都唯一
	 */
	public Doctor getDoctorByUsername(String username);
	
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
	public JSONObject patientLogin(String username, String password);

	/**
	 * 添加医生
	 */
	public JSONObject addDoctor(int doctorId, int patientId);
	
	/**
	 * 更新用户信息
	 */
	public JSONObject updateInfo(int patientId, String tag, String content);
}
