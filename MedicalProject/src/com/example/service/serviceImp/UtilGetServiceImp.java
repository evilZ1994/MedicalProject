package com.example.service.serviceImp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Data;
import com.example.bean.Doctor;
import com.example.bean.Feedback;
import com.example.bean.Patient;
import com.example.mapper.DataMapper;
import com.example.mapper.DoctorMapper;
import com.example.mapper.FeedbackMapper;
import com.example.mapper.PatientMapper;
import com.example.service.DoctorService;
import com.example.service.PatientService;
import com.example.service.UtilGetService;

@Service
public class UtilGetServiceImp implements UtilGetService {

	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private FeedbackMapper feedbackMapper;
	@Autowired
	private DataMapper dataMapper;
	
	@Override
	public void hasAddDoctor(JSONObject params, JSONObject result, PatientService patientService, PrintWriter writer) {
		try {
			Patient patient = patientService.getPatientByUsername(params.getString("username"));
			Doctor doctor = patient.getDoctor();
			if (doctor!=null) {
				result.put("result", 1);
				writer.write(result.toString());
			} else {
				result.put("result", 0);
				writer.write(result.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void searchDoctor(JSONObject params, JSONObject result, DoctorService doctorService, PrintWriter writer) {
		try {
			Doctor doctor = doctorService.getDoctorByUsername(params.getString("username"));
			if (doctor!=null) {
				JSONObject content = new JSONObject(doctor);
				result.put("result", 1);
				result.put("content", content);
				writer.write(result.toString());
			} else {
				result.put("result", 0);
				writer.write(result.toString());
			}
			System.out.println("result: "+result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/*@Override
	public void addDoctor(JSONObject params, JSONObject result, PatientService patientService, PrintWriter writer) {
		try {
			int patientId = params.getInt("patientId");
			int doctorId = params.getInt("doctorId");
			Patient patient = patientMapper.selectPatientById(patientId);
			if (patient.getDoctor()!=null) {
				result.put("result", -1);
				result.put("Error", "已添加医生！请勿重复添加！");
				writer.write(result.toString());
			} else {
				boolean isSuccess = patientService.addDoctor(doctorId, patientId);
				if (isSuccess) {
					result.put("result", 1);
					Doctor doctor = patientMapper.selectPatientById(patientId).getDoctor();
					JSONObject content = new JSONObject(doctor);
					result.put("content", content);
					writer.write(result.toString());
				} else {
					result.put("result", 0);
					result.put("Error", "添加失败！请检查网络或稍后再试！");
					writer.write(result.toString());
				}
			}
			System.out.println("result: "+result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public void getFeedback(JSONObject params, JSONObject result, PrintWriter writer) {
		List<Feedback> list = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		try {
			list = feedbackMapper.selectFeedbackByPatDocId(params.getInt("patient_id"), params.getInt("doctor_id"));
			if (list!=null) {
				Iterator<Feedback> iterator = list.iterator();
				while (iterator.hasNext()) {
					Feedback feedback = iterator.next();
					JSONObject jsonObject = new JSONObject(feedback);
					jsonArray.put(jsonObject);
				}
				result.put("result", "Success");
				result.put("content", jsonArray);
				writer.write(result.toString());
			} else {
				result.put("result", "Fail");
				writer.write(result.toString());
			}
			System.out.println("getFeedback result:"+result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateFeedback(JSONObject params) {
		List<Integer> list = new ArrayList<>();
		try {
			JSONArray jsonArray = params.getJSONArray("params");
			for(int index=0; index<jsonArray.length(); index++){
				JSONObject jsonObject = jsonArray.getJSONObject(index);
				list.add(jsonObject.getInt("id"));
			}
			System.out.println("List:"+list.toString());
			if (list!=null&&!list.isEmpty()) {
				feedbackMapper.updateBatch(list);				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void getPatientList(JSONObject params, PrintWriter writer) {
		try {
			int doctor_id = params.getInt("doctor_id");
			JSONArray patients = params.getJSONArray("patients");
			if (patients!=null&&patients.length()>0) {
				List<Integer> ids = new ArrayList<>();
				for(int index=0; index<patients.length(); index++){
					int id = patients.getJSONObject(index).getInt("patient_id");
					ids.add(id);
				}
				Map<String, Object> map = new HashMap<>();
				map.put("doctor_id", doctor_id);
				map.put("ids", ids);
				System.out.println("patient_ids:"+ids.toString());
				List<Patient> list = patientMapper.getPatientListByDoctorId2(doctor_id, ids);
				JSONArray jsonArray = new JSONArray();
				if (list!=null&&!list.isEmpty()) {
					Iterator<Patient> iterator = list.iterator();
					while(iterator.hasNext()){
						Patient patient = iterator.next();
						JSONObject jsonObject = new JSONObject(patient);
						jsonObject.remove("password");
						jsonObject.remove("doctor");
						jsonObject.put("doctor_id", doctor_id);
						jsonArray.put(jsonObject);
					}
				}
				System.out.println("patient_List:"+jsonArray.toString());
				writer.write(jsonArray.toString());
			} else{
				List<Patient> list = patientMapper.getPatientListByDoctorId(doctor_id);
				JSONArray jsonArray = new JSONArray();
				if (list!=null&&!list.isEmpty()) {
					Iterator<Patient> iterator = list.iterator();
					while(iterator.hasNext()){
						Patient patient = iterator.next();
						JSONObject jsonObject = new JSONObject(patient);
						jsonObject.remove("password");
						jsonObject.remove("doctor");
						jsonObject.put("doctor_id", doctor_id);
						jsonArray.put(jsonObject);
					}
				}
				System.out.println("patient_List:"+jsonArray.toString());
				writer.write(jsonArray.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
