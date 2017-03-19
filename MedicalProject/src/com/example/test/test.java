package com.example.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.bean.Doctor;
import com.example.bean.Feedback;
import com.example.bean.Patient;
import com.example.mapper.FeedbackMapper;
import com.example.mapper.PatientMapper;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("/Config/beans.xml")
public class test {

	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private FeedbackMapper feedbackMapper;
	
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
		List<Feedback> list = new ArrayList<>();
		list = feedbackMapper.selectFeedbackByPatDocId(1, 1);

		System.out.println(list.toString());
	}
}
