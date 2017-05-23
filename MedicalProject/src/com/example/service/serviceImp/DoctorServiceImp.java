package com.example.service.serviceImp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Doctor;
import com.example.bean.Patient;
import com.example.mapper.DoctorMapper;
import com.example.mapper.PatientMapper;
import com.example.service.DoctorService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
public class DoctorServiceImp implements DoctorService {

	@Autowired
	private DoctorMapper doctorMapper;
	@Autowired
	private PatientMapper patientMapper;

	@Override
	public Doctor getDoctorById(int id) {
		Doctor doctor = doctorMapper.selectDoctorById(id);
		return doctor;
	}

	@Override
	public Doctor getDoctorByUsername(String username) {
		Doctor doctor = doctorMapper.selectDoctorByUsername(username);
		return doctor;
	}

	@Override
	public Patient getPatientByUsername(String username) {
		Patient patient = patientMapper.selectPatientByUsername(username);
		return patient;
	}

	@Override
	public JSONObject doctorRegister(JSONObject content) throws JSONException {
		JSONObject resultJson = new JSONObject();
		String username = content.getString("username");
		Doctor checkDoc = getDoctorByUsername(username);
		Patient checkPat = getPatientByUsername(username);
		// 同时保证医生与患者的用户名唯一
		if (checkDoc == null && checkPat == null) {
			String name = content.getString("name");
			String password = content.getString("password");
			String hospital = content.getString("hospital");
			String department = content.getString("department");
			Date create_time = new Date();
			Date update_time = new Date();
			Doctor doctor = new Doctor();
			doctor.setUsername(username);
			doctor.setName(name);
			doctor.setPassword(password);
			doctor.setHospital(hospital);
			doctor.setDepartment(department);
			doctor.setCreate_time(create_time);
			doctor.setUpdate_time(update_time);

			doctorMapper.insertDoctor(doctor);
			// 检查注册是否成功
			Doctor doctor2 = getDoctorByUsername(doctor.getUsername());

			boolean registerSuccess = (doctor2 != null);
			if (registerSuccess) {
				resultJson.put("status", "success").put("message", "注册成功！").put("user",
						new JSONObject().put("username", username).put("password", password));
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
	public Doctor getByUsernameAndPass(String username, String password) {
		Doctor doctor = doctorMapper.selectByUsernameAndPass(new Doctor(username, password));
		return doctor;
	}

	@Override
	public JSONObject doctorLogin(String username, String password) {
		try {
			Doctor doctor = getByUsernameAndPass(username, password);
			if (doctor != null) {
				JSONObject jsonObject = new JSONObject(doctor);
				JSONObject result = new JSONObject();
				result.put("status", "success");
				result.put("user", jsonObject);
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

	/**
	 * 查找医生，返回id、用户名、姓名、医院组成的Json
	 */
	@Override
	public JSONObject searchDoctor(String username) {
		Doctor doctor = getDoctorByUsername(username);
		JSONObject result = new JSONObject();
		try {
			if (doctor != null) {
				// 查找到医生存在
				result.put("status", "success");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", doctor.getId()).put("username", doctor.getUsername()).put("name", doctor.getName())
						.put("hospital", doctor.getHospital());
				result.put("doctor", jsonObject);
				return result;
			} else {
				result.put("status", "fail");
				result.put("doctor", new JSONObject());
				return result;
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public JSONObject getPatientList(int doctor_id) {
		List<Patient> list = patientMapper.getPatientListByDoctorId(doctor_id);
		JSONArray jsonArray = new JSONArray();
		JSONObject resultObject = new JSONObject();
		try {
			if (list != null && !list.isEmpty()) {
				Iterator<Patient> iterator = list.iterator();
				while (iterator.hasNext()) {
					Patient patient = iterator.next();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", patient.getId());
					jsonObject.put("username", patient.getUsername());
					jsonObject.put("name", patient.getName());
					jsonArray.put(jsonObject);
				}
				resultObject.put("result", jsonArray);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	@Override
	public JSONObject updateInfo(int doctor_id, String tag, String content) {
		JSONObject jsonObject = new JSONObject();
		switch (tag) {
		case "name":
			doctorMapper.updateName(doctor_id, content);
			break;
		case "sex":
			doctorMapper.updateSex(doctor_id, content);
			break;
		case "hospital":
			doctorMapper.updateHospital(doctor_id, content);
			break;
		case "department":
			doctorMapper.updateDepartment(doctor_id, content);
			break;
		default:
			break;
		}
		try {
			jsonObject.put("status", "success").put("content", content);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
