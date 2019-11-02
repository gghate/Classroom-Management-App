package com.gghate.ExamAppl.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feerecord")
public class FeeRecord {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="record_date")
	private String recordDate;
	@Column(name="feepaid")
	private int feepaid;
	@Column(name="studentid")
	private int studentid;

	public FeeRecord() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String record_date) {
		this.recordDate = record_date;
	}
	public int getFeepaid() {
		return feepaid;
	}
	public void setFeepaid(int feepaid) {
		this.feepaid = feepaid;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	

}
