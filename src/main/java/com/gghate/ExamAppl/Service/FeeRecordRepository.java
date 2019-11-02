package com.gghate.ExamAppl.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gghate.ExamAppl.Entity.FeeRecord;

@Service
public interface FeeRecordRepository extends JpaRepository<FeeRecord, Integer> {

	public List<FeeRecord> findByStudentid(int id);
	
	public FeeRecord findByStudentidAndRecordDate(int studentid,String record_date);
} 
