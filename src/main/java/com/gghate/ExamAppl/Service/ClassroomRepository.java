package com.gghate.ExamAppl.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gghate.ExamAppl.Entity.Classroom;


@Service
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
   
	Classroom findByClassCode(String code);
}
