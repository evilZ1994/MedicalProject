package com.example.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.bean.Doctor;
import com.example.bean.Patient;
import com.example.mapper.PatientMapper;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("/Config/beans.xml")
public class test {

	@Autowired
	private PatientMapper patientMapper;
	
	/*@Test
	public void addPatient(){
		Patient patient = new Patient();
		patient.setName("Hilbert");
		patient.setUsername("Robot");
		patient.setPassword("123");
		patient.setCreate_time(new Date());
		patient.setUpdate_time(new Date());
		
		patientMapper.addPatient(patient);
	}*/
	@Test
	public void getPatient(){
		Patient patient = patientMapper.selectPatientById(1);
		Doctor doctor = patient.getDoctor();
		System.out.println(doctor.toString());
	}
}
