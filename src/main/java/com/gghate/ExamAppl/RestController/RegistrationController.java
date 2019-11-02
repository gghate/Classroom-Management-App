package com.gghate.ExamAppl.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gghate.ExamAppl.Entity.Classroom;
import com.gghate.ExamAppl.Entity.ClassroomStudentLink;
import com.gghate.ExamAppl.Entity.StudentMetaData;
import com.gghate.ExamAppl.Response.CustomError;
import com.gghate.ExamAppl.Response.Successresponse;
import com.gghate.ExamAppl.Security.AccountCredentials;
import com.gghate.ExamAppl.Service.ApplicationUserRepositor;
import com.gghate.ExamAppl.Service.ClassroomRepository;
import com.gghate.ExamAppl.Service.ClassroomStudentLinkRepository;


@RestController
public class RegistrationController {

	@Autowired
	ApplicationUserRepositor userRepository;
		
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	ClassroomStudentLinkRepository studentLinkRepository;
	
	@Autowired
	ClassroomRepository classroomRepository;
	
	@PostMapping("/signup")
	public AccountCredentials saveUser(@RequestBody AccountCredentials thAccount)
	{
        AccountCredentials tempAccount=userRepository.findByUsername(thAccount.getUsername());
        if(tempAccount!=null)
        {
        	throw new RuntimeException("Username already exists");
        }
		thAccount.setId(0);
		thAccount.setPassword(bCryptPasswordEncoder.encode(thAccount.getPassword()));
		AccountCredentials dbAccountCredentials=userRepository.save(thAccount);

		return dbAccountCredentials;
	}
	
	@PutMapping("/api/user/update")
	public AccountCredentials updateUser(@RequestBody AccountCredentials thAccount)
	{
		AccountCredentials tempAccount=userRepository.findByUsername(thAccount.getUsername());
        if(tempAccount!=null)
        {
        	throw new RuntimeException("Username already exists");
        }
		AccountCredentials user=userRepository.getOne(thAccount.getId());
		user.setUsername(thAccount.getUsername());
		AccountCredentials dbAccountCredentials=userRepository.save(user);

		return dbAccountCredentials;
	}
	
	@GetMapping("/getUserByUsername/{username}")
	public StudentMetaData getByUsername(@PathVariable String username)
	{
		 AccountCredentials user=userRepository.findByUsername(username);
         List<ClassroomStudentLink> classroomForStudents=studentLinkRepository.findByStudentID(user.getId());
         List<Classroom> classrooms=new ArrayList<Classroom>();
		 if(classroomForStudents.size()!=0)
		 {
			 for(int i=0;i<classroomForStudents.size();i++)
			 {
				 classrooms.add(classroomRepository.findByClassCode(classroomForStudents.get(i).getClassroomCode()));
			 } 
				 
		 }
		
		
		 StudentMetaData metaData=new StudentMetaData();
		 metaData.setId(user.getId());
		 metaData.setUsername(user.getUsername());
		 metaData.setFirstName(user.getFirstName());
		 metaData.setLastName(user.getLastName());
		 metaData.setClassroomList(classrooms);
		 metaData.setPassword(user.getPassword());
		 metaData.setPhoneNumber(user.getPhoneNumber());
		 metaData.setEnabled(user.getEnabled());
		 metaData.setStd(user.getStd());
		 
		return metaData;
	}
	 @GetMapping("/api/enableUser/{id}")
	 public ResponseEntity<Successresponse> enableUser(@PathVariable int id)
	 {
		AccountCredentials userCredentials=userRepository.getOne(id);
		if(userCredentials==null)
		{
			throw new RuntimeException("User not found");
		}
		userCredentials.setEnabled(true);
		userRepository.save(userCredentials);
		Successresponse customError=new Successresponse();
		customError.setMessage("Successfullly enabled user");
		return new ResponseEntity<>(customError,HttpStatus.OK);
	 } 
	 @GetMapping("/api/getDisabledUser")
	 public List<AccountCredentials> getDisabledUser()
	 {
		 List<AccountCredentials> accountCredentials=userRepository.findByEnabled(false);
		 return accountCredentials;
	 }
	 @GetMapping("/api/getClassUsers/{std}")
	 public List<AccountCredentials> getClassUsers(@PathVariable String std)
	 {
		 List<AccountCredentials> accountCredentials=userRepository.findByStd(std);
		 return accountCredentials;
	 }
	 @GetMapping("/api/forgot/password/{username}/{newP}")
	 public ResponseEntity<Successresponse> forgetPassword(@PathVariable String username,@PathVariable String newP)
	 {
		 AccountCredentials userCredentials=userRepository.findByUsername(username);
			if(userCredentials==null)
			{
				throw new RuntimeException("User not found");
			}
			userCredentials.setPassword(bCryptPasswordEncoder.encode(newP));
			userRepository.save(userCredentials);
			Successresponse customError=new Successresponse();
			customError.setMessage("Successfullly Changed Password");
			return new ResponseEntity<>(customError,HttpStatus.OK);
			
	 }
	 @DeleteMapping("/api/user/{id}")
	 public ResponseEntity<Successresponse> deleteUserById(@PathVariable int id)
	 {
		 AccountCredentials userCredentials=userRepository.getOne(id);
			if(userCredentials==null)
			{
				throw new RuntimeException("User not found");
			}
			userRepository.delete(userCredentials);
			Successresponse customError=new Successresponse();
			customError.setMessage("Successfullly deleted user");
			return new ResponseEntity<>(customError,HttpStatus.OK);
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
