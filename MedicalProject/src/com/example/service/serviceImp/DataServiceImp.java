package com.example.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextListener;

import com.example.bean.Data;
import com.example.mapper.DataMapper;
import com.example.service.DataService;

import Utils.DateUtil;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
		System.out.println("Date:" + new Date() + "\nJsonArray:" + jsonArray.toString());

		List<Data> dataList = new ArrayList<>();
		if (jsonArray != null && jsonArray.length() > 0) {
			for (int index = 0; index < jsonArray.length(); index++) {
				try {
					Data data = new Data();
					JSONObject jsonObject = jsonArray.getJSONObject(index);
					data.setPressure(jsonObject.getInt("pressure"));
					data.setAngle(jsonObject.getDouble("angle"));
					data.setTemperature(jsonObject.getDouble("temperature"));
					data.setPulse(jsonObject.getInt("pulse"));
					data.setCreate_time(DateUtil.parse(jsonObject.getString("create_time")));
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
			boolean isSuccess = dataMapper.insertBatchData(dataList) > 0;
			return isSuccess;
		}
		return false;
	}

	@Override
	public void getData(JSONObject params, JSONObject result, PrintWriter writer) {
		try {
			int patient_id = params.getInt("patient_id");
			List<Data> dataList = dataMapper.getDataList(patient_id);
			if (dataList != null && !dataList.isEmpty()) {
				result.put("result", "Success");
				JSONArray jsonArray = new JSONArray();
				Iterator<Data> iterator = dataList.iterator();
				while (iterator.hasNext()) {
					JSONObject jsonObject = new JSONObject(iterator.next());
					jsonArray.put(jsonObject);
				}
				result.put("content", jsonArray);
				System.out.println("getData:" + result.toString());
				writer.write(result.toString());
			} else {
				result.put("result", "Nothing");
				writer.write(result.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateData(JSONObject params, JSONObject result, PrintWriter writer) {
		try {
			JSONArray jsonArray = params.getJSONArray("params");
			List<Integer> ids = new ArrayList<>();
			for (int i = 0; i < jsonArray.length(); i++) {
				ids.add(jsonArray.getJSONObject(i).getInt("id"));
			}
			System.out.println("ids:" + ids.toString());
			dataMapper.updateDataSetRead(ids);
			writer.write("Done");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getDataByPatientId(int patient_id) {
		List<Data> list = dataMapper.get12Data(patient_id);
		JSONObject result = new JSONObject();
		if (list != null && list.size() > 0) {
			JSONArray jsonArray = new JSONArray();
			for (Data data : list) {
				JSONObject jsonObject = new JSONObject(data);
				try {
					// 修改Date格式
					jsonObject.put("create_time", DateUtil.format(data.getCreate_time()));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				jsonArray.put(jsonObject);
			}
			try {
				result.put("data", jsonArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public JSONObject getDetailData(int id, String tag) {
		JSONObject result = new JSONObject();
		List<Data> list = dataMapper.get100Data(id);
		if (list != null && list.size() > 0) {
			try {
				switch (tag) {
				case "pressure":
					JSONArray pressureArray = new JSONArray();
					for (Data data : list) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("pressure", data.getPressure());
						jsonObject.put("create_time", DateUtil.format(data.getCreate_time()));
						pressureArray.put(jsonObject);
					}
					result.put("result", pressureArray);
					result.put("tag", tag);
					break;
				case "temperature":
					JSONArray temArray = new JSONArray();
					for (Data data : list) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("temperature", data.getTemperature());
						jsonObject.put("create_time", DateUtil.format(data.getCreate_time()));
						temArray.put(jsonObject);
					}
					result.put("result", temArray);
					result.put("tag", tag);
					break;
				case "pulse":
					JSONArray pulseArray = new JSONArray();
					for (Data data : list) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("pulse", data.getPulse());
						jsonObject.put("create_time", DateUtil.format(data.getCreate_time()));
						pulseArray.put(jsonObject);
					}
					result.put("result", pulseArray);
					result.put("tag", tag);
					break;
				case "angle":
					JSONArray angleArray = new JSONArray();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("angle", list.get(0).getPulse());
					jsonObject.put("create_time", DateUtil.format(list.get(0).getCreate_time()));
					angleArray.put(jsonObject);
					result.put("result", angleArray);
					result.put("tag", tag);
				default:
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
