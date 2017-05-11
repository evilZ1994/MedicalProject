package com.example.mapper;

import com.example.bean.Doctor;

public interface DoctorMapper {
	
	Doctor selectDoctorById(int id);
	
	Doctor selectDoctorByUsername(String username);
	
	void insertDoctor(Doctor doctor);

	Doctor selectByUsernameAndPass(Doctor doctor);
}
