package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Data;
import com.example.mapper.DataMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Service
public class DataServiceImp implements DataService {
	
	@Autowired
	private DataMapper dataMapper;

	@Override
	public boolean insertBatchData(JSONArray jsonArray) {
		System.out.println("Date:"+new Date()+"\nJsonArray:"+jsonArray.toString());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Data> dataList = new ArrayList<>();
		if (jsonArray!=null) {
			for(int index=0; index<jsonArray.length(); index++){
				try {
					Data data = new Data();
					JSONObject jsonObject = jsonArray.getJSONObject(index);
					data.setPressure(jsonObject.getInt("pressure"));
					data.setAngle(jsonObject.getDouble("angle"));
					data.setTemperature(jsonObject.getDouble("temperature"));
					data.setPulse(jsonObject.getInt("pulse"));
					data.setCreate_time(format.parse(jsonObject.getString("create_time")));
					data.setPatient_id(jsonObject.getInt("patient_id"));
					data.setHas_read(0);
					dataList.add(data);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			boolean isSuccess = dataMapper.insertBatchData(dataList)>0;
			return isSuccess;
		}
		return false;
	}
}
