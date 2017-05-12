package com.example.service;

import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DataService {
	
	public boolean insertBatchData(JSONArray jsonArray);
	
	public void getData(JSONObject params, JSONObject result, PrintWriter writer);

	public void updateData(JSONObject params, JSONObject result, PrintWriter writer);
	
	/**
	 * 返回最新的12条数据
	 * @param id
	 * @return
	 */
	public JSONObject getDataByPatientId(int id);
}
