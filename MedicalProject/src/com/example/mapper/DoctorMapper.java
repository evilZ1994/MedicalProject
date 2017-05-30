package com.example.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.bean.Doctor;
import com.sun.xml.internal.bind.v2.model.core.ID;

public interface DoctorMapper {
	
	Doctor selectDoctorById(int id);
	
	Doctor selectDoctorByUsername(String username);
	
	void insertDoctor(Doctor doctor);

	Doctor selectByUsernameAndPass(Doctor doctor);
	
	Doctor selectByIdAndPass(@Param("id")int id, @Param("password")String password);
	
	void updateName(@Param("id")int id,@Param("name")String name);
	
	void updateSex(@Param("id")int id,@Param("sex")String sex);
	
	void updateHospital(@Param("id")int id,@Param("hospital")String hospital);
	
	void updateDepartment(@Param("id")int id,@Param("department")String department);
	
	void updatePassword(@Param("id")int id, @Param("password")String password);
}
