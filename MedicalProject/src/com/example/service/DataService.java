package com.example.service;

import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DataService {
	
	public boolean insertBatchData(JSONArray jsonArray);
	
	void getData(JSONObject params, JSONObject result, PrintWriter writer);

	public void updateData(JSONObject params, JSONObject result, PrintWriter writer);
}
