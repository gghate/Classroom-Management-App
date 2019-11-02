package com.gghate.ExamAppl.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gghate.ExamAppl.Entity.ClassroomStudentLink;

@Service
public interface ClassroomStudentLinkRepository extends JpaRepository<ClassroomStudentLink, Integer> {

	ClassroomStudentLink findByClassroomCodeAndStudentID(String classroomCode,int Studentid);
	
	ClassroomStudentLink findById(int id);
	
	List<ClassroomStudentLink> findByStudentID(int Studentid);
	List<ClassroomStudentLink> findByClassroomCode(String classCode);
}
