package com.gghate.ExamAppl.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feemodel")
public class FeeModel {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="totalfee")
	private int totalfee;
	@Column(name="feepaid")
	private int feepaid;
	@Column(name="studentid")
	private int studentid;

	public FeeModel() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(int totalfee) {
		this.totalfee = totalfee;
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
