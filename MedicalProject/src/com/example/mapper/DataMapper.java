package com.example.mapper;

import java.util.List;

import com.example.bean.Data;
import com.example.bean.Doctor;

public interface DataMapper {
	
	void insertData(Data data);
	
	int insertBatchData(List<Data> dataList);

	List<Data> getDataList(int patient_id);

	void updateDataSetRead(List<Integer> ids);
	
}
