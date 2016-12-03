package com.example.service.serviceImp;

import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Doctor;
import com.example.bean.Patient;
import com.example.mapper.DoctorMapper;
import com.example.mapper.PatientMapper;
import com.example.service.DoctorService;
import com.example.service.PatientService;
import com.example.service.UtilGetService;

@Service
public class UtilGetServiceImp implements UtilGetService {

	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private DoctorMapper doctorMapper;
	
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

	@Override
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
	}
}
