package com.example.mapper;

import com.example.bean.Doctor;

public interface DoctorMapper {
	
	Doctor selectDoctorById(int id);
	
	Doctor selectDoctorByUsername(String username);
	
	void addDoctor(Doctor doctor);

	Doctor selectByUsernameAndPass(Doctor doctor);
}
