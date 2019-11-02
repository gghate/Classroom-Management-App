package com.gghate.ExamAppl.RestController;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gghate.ExamAppl.Entity.Classroom;
import com.gghate.ExamAppl.Entity.ClassroomStudentLink;
import com.gghate.ExamAppl.Entity.Notes;
import com.gghate.ExamAppl.Response.CustomError;
import com.gghate.ExamAppl.Response.Successresponse;
import com.gghate.ExamAppl.Service.ClassroomRepository;
import com.gghate.ExamAppl.Service.ClassroomStudentLinkRepository;
import com.gghate.ExamAppl.Service.NotesRepository;

@RestController
@RequestMapping("/api")
public class ClassRoomController {

	@Autowired
	ClassroomRepository classroomRepository;
	@Autowired
	NotesRepository notesRepository;
	@Autowired
	ClassroomStudentLinkRepository classStudentLinkRepo;
	@GetMapping("/classroom")
	public List<Classroom> getClassrooms()
	{
		
		List<Classroom> classroom=classroomRepository.findAll();
		return classroom;
	}
	
	@PutMapping("/classroom")
	public Classroom updateClassroom(@RequestBody Classroom theClassroom)
	{
		
		Classroom classroom=classroomRepository.save(theClassroom);
		return classroom;
	}
	
	@PostMapping("/classroom")
	public Classroom saveClassroom(@RequestBody Classroom theClassroom)
	{
		if(classroomRepository.findByClassCode(theClassroom.getClassCode())!=null)
		{
			throw new RuntimeException("Please provide different Class Code");
		}
		theClassroom.setClassId(0);
		Classroom classroom=classroomRepository.save(theClassroom);
		return classroom;
	}
	
	@GetMapping("/classroom/{theId}")
	public Classroom getById(@PathVariable int theId)
	{
	
		Classroom classroom=classroomRepository.getOne(theId);
		return classroom;
	}
	@DeleteMapping("/classroom/{theId}")
	public Successresponse deleteClassroom(@PathVariable int theId)
	{
		Classroom classroom=classroomRepository.getOne(theId);
		if(classroom==null)
		{
			throw new RuntimeException("No Class found");
		}
		String classCode=classroom.getClassCode();
		List<ClassroomStudentLink> classroomLink=classStudentLinkRepo.findByClassroomCode(classCode);
		List<Notes> notesLink=notesRepository.findByClassCode(classCode);
		notesRepository.deleteInBatch(notesLink);
		classStudentLinkRepo.deleteInBatch(classroomLink);
		classroomRepository.delete(classroom);
		Successresponse reponseObject=new Successresponse();
		reponseObject.setMessage("classroom deleted successfully");
	    return reponseObject;
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
