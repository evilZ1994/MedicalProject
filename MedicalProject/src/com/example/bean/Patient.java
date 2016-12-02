package com.example.bean;

import java.util.Date;

public class Patient {
	private int id;
	private String sex;
	private String username;
	private String name;
	private String password;
	private String IDnumber;
	private String department;
	private String nation;
	private String marriage;
	private String address;
	private Date check_time;
	private Date record_time;
	private Doctor doctor;
	private Date create_time;
	private Date update_time;
	
	public Patient() {
		super();
	}
	
	public Patient(int id, String username, String name, String password, Doctor doctor, Date create_time,
			Date update_time) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.doctor = doctor;
		this.create_time = create_time;
		this.update_time = update_time;
	}

	public Patient(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIDnumber() {
		return IDnumber;
	}

	public void setIDnumber(String iDnumber) {
		IDnumber = iDnumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	public Date getRecord_time() {
		return record_time;
	}

	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password
				+ ", doctor=" + doctor + ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
	
}
