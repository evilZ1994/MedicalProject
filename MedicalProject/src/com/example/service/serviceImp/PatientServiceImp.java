package com.example.service.serviceImp;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Doctor;
import com.example.bean.Patient;
import com.example.mapper.PatientMapper;
import com.example.service.PatientService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
public class PatientServiceImp implements PatientService {

	@Autowired
	private PatientMapper patientMapper;

	@Override
	public Patient getPatientByUsername(String username) {
		Patient patient = patientMapper.selectPatientByUsername(username);
		return patient;
	}

	public JSONObject patientRegister(JSONObject content) throws JSONException {
		JSONObject resultJson = new JSONObject();
		String username = content.getString("username");
		Patient checkPat = getPatientByUsername(username);
		if (checkPat==null) {
			String name = content.getString("name");
			String password = content.getString("password");
			Date create_time = new Date();
			Date update_time = new Date();
			Patient patient = new Patient();
			patient.setUsername(username);
			patient.setName(name);
			patient.setPassword(password);
			patient.setCreate_time(create_time);
			patient.setUpdate_time(update_time);
			
			patientMapper.addPatient(patient);
			//检查注册是否成功
			Patient patient2 = getPatientByUsername(patient.getUsername());
			
			boolean registerSuccess = (patient2!=null);
			if (registerSuccess) {
				resultJson.put("status", "success").put("message", "注册成功！").put("user", new JSONObject().put("username", username).put("password", password));
			} else {
				resultJson.put("status", "fail").put("message", "注册失败！").put("user", new JSONObject());
			}
			return resultJson;
		} else {
			resultJson.put("status", "fail").put("message", "用户名已存在！").put("user", new JSONObject());
			return resultJson;
		}
	}

	@Override
	public Patient getByUsernameAndPass(String username, String password) {
		Patient patient = patientMapper.selectByUsernameAndPass(new Patient(username, password));
		return patient;
	}

	@Override
	public JSONObject patientLogin(String username, String password) {
		try {
			Patient patient = getByUsernameAndPass(username, password);
			if (patient != null) {
				JSONObject jsonObject = new JSONObject(patient);
				JSONObject result = new JSONObject();
				result.put("status", "success");
				result.put("user", jsonObject);
				result.put("message", "");
				return result;
			} else {
				JSONObject result = new JSONObject();
				result.put("status", "fail");
				result.put("user", "");
				result.put("message", "用户名或密码错误！");
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addDoctor(int doctorId, int patientId) {
		patientMapper.addDoctor(doctorId, patientId);
		Patient patient = patientMapper.selectPatientById(patientId);
		Doctor doctor = patient.getDoctor();
		if (doctor!=null) {
			return true;
		}
		return false;
	}

}
