package com.example.service;

import java.io.PrintWriter;

import org.json.JSONObject;

public interface UtilGetService {

	void hasAddDoctor(JSONObject params, JSONObject result, PatientService patientService, PrintWriter writer);

	void searchDoctor(JSONObject params, JSONObject result, DoctorService doctorService, PrintWriter writer);
}
