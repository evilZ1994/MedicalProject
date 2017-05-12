package com.example.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Doctor;
import com.example.bean.Patient;

public interface DoctorService {
	/**
	 * 根据id查找用户
	 */
	public Doctor getDoctorById(int id);
	
	/**
	 * 根据用户名检查用户是否存在
	 */
	public Doctor getDoctorByUsername(String username);
	
	/**
	 * 保证JMessage上用户名唯一性，患者和医生用户都唯一
	 */
	public Patient getPatientByUsername(String username);
	
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
	public JSONObject doctorLogin(String username, String password);
	
	/**
	 * 查找医生
	 */
	public JSONObject searchDoctor(String username);
	
	/**
	 * 获取患者列表
	 */
	public JSONObject getPatientList(int doctor_id);
}
