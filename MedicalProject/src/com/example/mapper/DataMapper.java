package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.bean.Data;
import com.example.bean.Doctor;

public interface DataMapper {
	
	void insertData(Data data);
	
	int insertBatchData(List<Data> dataList);

	List<Data> getDataList(int patient_id);

	void updateDataSetRead(List<Integer> ids);
	
	List<Data> get12Data(@Param(value="patient_id") int patient_id);
	
	List<Data> get100Data(@Param(value="patient_id") int patient_id);
}
