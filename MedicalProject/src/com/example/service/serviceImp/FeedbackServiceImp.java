package com.example.service.serviceImp;

import java.io.PrintWriter;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Doctor;
import com.example.bean.Feedback;
import com.example.bean.Patient;
import com.example.mapper.FeedbackMapper;
import com.example.service.FeedbackService;

@Service
public class FeedbackServiceImp implements FeedbackService {
	
	@Autowired
	FeedbackMapper feedbackMapper;

	@Override
	public void addFeedback(JSONObject jsonObject, PrintWriter writer) {
		Feedback feedback = new Feedback();
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		try {
			doctor.setId(jsonObject.getInt("doctor_id"));
			patient.setId(jsonObject.getInt("patient_id"));
			
			feedback.setPatient(patient);
			feedback.setDoctor(doctor);
			feedback.setCreate_time(new Date());
			feedback.setContent(jsonObject.toString());
			
			int isSuccess = feedbackMapper.addFeedback(feedback);
			if (isSuccess>0) {
				System.out.println("insert feedback success!");
				writer.write("Success");
			} else {
				System.out.println("insert feedback fail!");
				writer.write("Fail");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
