package com.example.bean;

import java.util.Date;

public class Feedback {
	private String content;
	private int patient_id;
	private int doctor_id;
	private Date create_time;
	private Date update_time;
	private int has_read;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
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
