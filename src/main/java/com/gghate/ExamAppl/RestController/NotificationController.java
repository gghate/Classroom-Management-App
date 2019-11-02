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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gghate.ExamAppl.Entity.Notification;
import com.gghate.ExamAppl.Response.CustomError;
import com.gghate.ExamAppl.Response.Successresponse;
import com.gghate.ExamAppl.Service.notificationRepository;

@RestController
@RequestMapping("/api")
public class NotificationController{
	
	@Autowired
	notificationRepository repository;

	@PostMapping("/notification")
	public Notification postNotification(@RequestBody Notification theNotification)
	{
		theNotification.setId(0);
		Notification savedNotification=repository.save(theNotification);
		return savedNotification;
	}
	
	@GetMapping("/notification/{id}/{std}")
	public List<Notification> getNewNotification(@PathVariable int id,@PathVariable String std)
	{
		List<Notification> notification=repository.getNewNotification(id,std);
	
		return notification;
	}
	
	
	@GetMapping("/notification")
	public List<Notification> getAllNotification()
	{
		List<Notification> notifications=repository.findAll();
		return notifications;
	}
	
	@DeleteMapping("/notification/{id}")
	public Successresponse deleteNotification(@PathVariable int id)
	{
		Notification notification=repository.getOne(id);
		if(notification==null)
		{
			throw new RuntimeException("Not found");
		}
		Successresponse reponseObject=new Successresponse();
		reponseObject.setMessage("notes deleted successfully");
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
