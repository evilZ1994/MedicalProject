
package com.example.service.serviceImp;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Doctor;
import com.example.bean.Patient;
import com.example.mapper.DoctorMapper;
import com.example.mapper.PatientMapper;
import com.example.service.PatientService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
public class PatientServiceImp implements PatientService {

	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private DoctorMapper doctorMapper;
	
	@Override
	public Patient getPatientById(int id) {
		Patient patient = patientMapper.selectPatientById(id);
		return patient;
	}

	@Override
	public Patient getPatientByUsername(String username) {
		Patient patient = patientMapper.selectPatientByUsername(username);
		return patient;
	}
	
	@Override
	public Doctor getDoctorByUsername(String username) {
		Doctor doctor = doctorMapper.selectDoctorByUsername(username);
		return doctor;
	}

	public JSONObject patientRegister(JSONObject content) throws JSONException {
		JSONObject resultJson = new JSONObject();
		String username = content.getString("username");
		Patient checkPat = getPatientByUsername(username);
		Doctor checkDoc = getDoctorByUsername(username);
		//同时保证患者与医生用户名唯一
		if (checkPat==null && checkDoc==null) {
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
				resultJson.put("status", "fail").put("message", "注册失败了，稍后再试一下吧！").put("user", new JSONObject());
			}
			return resultJson;
		} else {
			resultJson.put("status", "fail").put("message", "用户名已存在，换一个试试吧！").put("user", new JSONObject());
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
				JSONObject patientObject = new JSONObject();
				JSONObject result = new JSONObject();
				result.put("status", "success");
				patientObject.put("id", patient.getId())
						.put("username", patient.getUsername())
						.put("password", patient.getPassword())
						.put("name", patient.getName());
				if (patient.getDoctor() != null) {
					patientObject.put("doctor_id", patient.getDoctor().getId());
					patientObject.put("doctor_username", patient.getDoctor().getUsername());
					patientObject.put("doctor_name", patient.getDoctor().getName());
				} else{
					patientObject.put("doctor_id", 0);
					patientObject.put("doctor_username", "");
					patientObject.put("doctor_name", "");
				}
				result.put("user", patientObject);
				result.put("message", "");
				return result;
			} else {
				JSONObject result = new JSONObject();
				result.put("status", "fail");
				result.put("user", new JSONObject());
				result.put("message", "用户名或密码错误！");
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public JSONObject addDoctor(int doctorId, int patientId) {
		patientMapper.addDoctor(doctorId, patientId);
		Patient patient = patientMapper.selectPatientById(patientId);
		Doctor doctor = patient.getDoctor();
		try {
			if (doctor.getId() == doctorId) {
				JSONObject result = new JSONObject();
				result.put("status", "success").put("doctor_id", doctor.getId()).put("doctor_name", doctor.getName()).put("doctor_username", doctor.getUsername());
				return result;
			} else {
				JSONObject result = new JSONObject();
				result.put("status", "fail").put("doctor_id", "").put("doctor_name", "").put("doctor_username", "");
				return result;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
