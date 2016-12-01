package com.example.service.serviceImp;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Patient;
import com.example.mapper.PatientMapper;
import com.example.service.PatientService;

import net.sf.json.JsonConfig;

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

}
