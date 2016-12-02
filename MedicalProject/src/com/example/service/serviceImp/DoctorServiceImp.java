package com.example.service.serviceImp;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Doctor;
import com.example.mapper.DoctorMapper;
import com.example.service.DoctorService;

@Service
public class DoctorServiceImp implements DoctorService {
	
	@Autowired
	private DoctorMapper doctorMapper;

	@Override
	public Doctor getDoctorByUsername(String username) {
		Doctor doctor = doctorMapper.selectDoctorByUsername(username);
		return doctor;
	}

	@Override
	public JSONObject doctorRegister(JSONObject content) throws JSONException {
		JSONObject resultJson = new JSONObject();
		String username = content.getString("username");
		Doctor checkDoc = getDoctorByUsername(username);
		if (checkDoc==null) {
			String name = content.getString("name");
			String password = content.getString("password");
			String hospital = content.getString("hospital");
			Date create_time = new Date();
			Date update_time = new Date();
			Doctor doctor = new Doctor();
			doctor.setUsername(username);
			doctor.setName(name);
			doctor.setPassword(password);
			doctor.setHospital(hospital);
			doctor.setCreate_time(create_time);
			doctor.setUpdate_time(update_time);
			
			doctorMapper.addDoctor(doctor);
			//检查注册是否成功
			Doctor doctor2 = getDoctorByUsername(doctor.getUsername());
			
			boolean registerSuccess = (doctor2!=null);
			if (registerSuccess) {
				resultJson.put("Success", "注册成功！");
			} else {
				resultJson.put("Error", "注册失败，请稍后再试！");
			}
			return resultJson;
		} else {
			resultJson.put("Error", "用户名已存在！");
			return resultJson;
		}
	}

	@Override
	public Doctor getByUsernameAndPass(String username, String password) {
		Doctor doctor = doctorMapper.selectByUsernameAndPass(new Doctor(username, password));
		return doctor;
	}

	@Override
	public JSONObject doctorLogin(JSONObject content) {
		try {
			String username = content.getString("username");
			String password = content.getString("password");
			Doctor doctor = getByUsernameAndPass(username, password);
			if (doctor != null) {
				JSONObject jsonObject = new JSONObject(doctor);
				JSONObject result = new JSONObject();
				result.put("status", "Success");
				result.put("content", jsonObject);
				return result;
			} else {
				JSONObject result = new JSONObject();
				result.put("status", "Fail");
				result.put("Error", "用户名或密码错误！");
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
