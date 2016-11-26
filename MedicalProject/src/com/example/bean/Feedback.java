package com.example.bean;

import java.util.Date;

public class Feedback {
	private int id;
	private String content;
	private Patient patient;
	private Doctor doctor;
	private Date create_time;
	private Date update_time;
	private int has_read;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getHas_read() {
		return has_read;
	}
	public void setHas_read(int has_read) {
		this.has_read = has_read;
	}
}
