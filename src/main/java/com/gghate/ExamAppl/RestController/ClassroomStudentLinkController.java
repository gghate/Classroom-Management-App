package com.gghate.ExamAppl.RestController;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gghate.ExamAppl.Entity.Classroom;
import com.gghate.ExamAppl.Entity.ClassroomStudentLink;
import com.gghate.ExamAppl.Response.CustomError;
import com.gghate.ExamAppl.Service.ClassroomRepository;
import com.gghate.ExamAppl.Service.ClassroomStudentLinkRepository;



@RestController
@RequestMapping("/api")
public class ClassroomStudentLinkController {

	@Autowired
	ClassroomStudentLinkRepository studentLinkRepository;
	
	@Autowired
	ClassroomRepository classroomRepository;
	
	
	 @PostMapping("/classroomStudentLink")
	 public Classroom registerStudentForClass(@RequestBody ClassroomStudentLink theClassroomStudentLink)
	 {
		 Classroom classroom=classroomRepository.findByClassCode(theClassroomStudentLink.getClassroomCode());
		 if(classroom==null)
		 {
			 throw new RuntimeException("No class found for given code");
		 }
		 ClassroomStudentLink checkLink=studentLinkRepository.findByClassroomCodeAndStudentID(theClassroomStudentLink.getClassroomCode(),
				                                                                         theClassroomStudentLink.getStudentID());
		 if(checkLink!=null)
		 {
			 throw new RuntimeException("Already registered");
		 }
		 theClassroomStudentLink.setId(0);
		 studentLinkRepository.save(theClassroomStudentLink);
		 return classroom;
	 }
	 
	 @PutMapping("/classroomStudentLink")
	 public ClassroomStudentLink updateClassroomStudentLink(@RequestBody ClassroomStudentLink theClassroomStudentLink)
	 {
		 Classroom classroom=classroomRepository.findByClassCode(theClassroomStudentLink.getClassroomCode());
		 if(classroom==null)
		 {
			 throw new RuntimeException("No class found for given code");
		 }
		 
		 studentLinkRepository.save(theClassroomStudentLink);
		 return theClassroomStudentLink;
	 }
	 
	 @GetMapping("/classroomStudentLink")
	 public List<ClassroomStudentLink> getClassroomStudentLink()
	 {
		 List<ClassroomStudentLink> classroomForStudents=studentLinkRepository.findAll();
	
		 return classroomForStudents;
	 }
	 
	 @GetMapping("/classroomStudentLink/{id}")
	 public ClassroomStudentLink getClassroomStudentLinkById(@PathVariable int id)
	 {
		 ClassroomStudentLink classroomForStudents=studentLinkRepository.findById(id);
		 
		 if(classroomForStudents==null)
		 {
			 
				 throw new RuntimeException("No class found for given student");
		 }
		 System.out.println(classroomForStudents.getStudentID());
		 return classroomForStudents;
	 }
	 
	 @GetMapping("/classroomStudentLink/student/{studentId}")
	 public List<Classroom> getClassroomForStudentById(@PathVariable int studentId)
	 {
		 List<ClassroomStudentLink> classroomForStudents=studentLinkRepository.findByStudentID(studentId);
		 
		 if(classroomForStudents.size()==0)
		 {
			 
				 throw new RuntimeException("No classes found for given student");
		 }
		 List<Classroom> classrooms=new ArrayList<Classroom>();
		 for(int i=0;i<classroomForStudents.size();i++)
		 {
			 classrooms.add(classroomRepository.findByClassCode(classroomForStudents.get(i).getClassroomCode()));
		 }
		 return classrooms;
	 }
	 @ExceptionHandler
	 public ResponseEntity<CustomError> handleException(Exception exc)
	 {
		CustomError customError=new CustomError();
		customError.setTitle("Exception");
		customError.setMessage(exc.getMessage());
		return new ResponseEntity<>(customError,HttpStatus.NOT_FOUND);
	 }
}
