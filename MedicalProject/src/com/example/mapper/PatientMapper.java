package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.bean.Patient;

public interface PatientMapper {
	
	Patient selectPatientById(int id);
	
	Patient selectPatientByUsername(String username);
	
	Patient selectByUsernameAndPass(Patient patient);
	
	void addPatient(Patient patient);

	void addDoctor(int doctor_id, int patient_id);
	
	List<Patient> getPatientListByDoctorId(int doctor_id);

	List<Patient> getPatientListByDoctorId2(@Param("doctor_id")int doctor_id, @Param("list")List<Integer> ids);

	void updateName(@Param("id")int patient_id, @Param("name")String name);
	
	void updateAge(@Param("id")int patient_id, @Param("age")int age);
	
	void updateSex(@Param("id")int patient_id, @Param("sex")String sex);
}
