package com.gghate.ExamAppl.Entity;

import java.util.List;

public class StudentMetaData {
  
	private int id;
	private String username;
    private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Boolean enabled;
	private String std;
	private List<Classroom> classroomList;
	public int getId() {
		return id;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	
	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
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

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Classroom> getClassroomList() {
		return classroomList;
	}
	public void setClassroomList(List<Classroom> classroomList) {
		this.classroomList = classroomList;
	}
	
	
}
