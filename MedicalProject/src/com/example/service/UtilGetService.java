package com.example.service;

import java.io.PrintWriter;

import org.json.JSONObject;

public interface UtilGetService {

	void hasAddDoctor(JSONObject params, JSONObject result, PatientService patientService, PrintWriter writer);

	void searchDoctor(JSONObject params, JSONObject result, DoctorService doctorService, PrintWriter writer);

	void addDoctor(JSONObject params, JSONObject result, PatientService patientService, PrintWriter writer);

	void getFeedback(JSONObject params, JSONObject result, PrintWriter writer);

	void updateFeedback(JSONObject params);
}
