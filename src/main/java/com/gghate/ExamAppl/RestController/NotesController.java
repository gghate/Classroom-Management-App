package com.gghate.ExamAppl.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.gghate.ExamAppl.Entity.Notes;
import com.gghate.ExamAppl.Entity.Notification;
import com.gghate.ExamAppl.Service.NotesRepository;
import com.gghate.ExamAppl.Service.notificationRepository;
import com.gghate.ExamAppl.Properties.FileStorageProperties;
import com.gghate.ExamAppl.Response.CustomError;
import com.gghate.ExamAppl.Response.Successresponse;

@RestController
@RequestMapping("/api")
public class NotesController {

	@Autowired
	NotesRepository notesRepository;
	@Autowired
	notificationRepository NotificationRepository;
	
	@Autowired
	FileStorageProperties fileStorageProperties;
	
	@GetMapping("/notes/new/{id}")
	public List<Notes> getNewNotes(@PathVariable int id)
	{
		List<Notes> notes=notesRepository.getNewNotes(id);
		return notes;
	}
	@GetMapping("/notes/{classCode}")
	public List<Notes> getAllNotes(@PathVariable String classCode)
	{
		List<Notes> notes=notesRepository.findByClassCode(classCode);
		return notes;
	}
	@PostMapping("/notes")
	public Notes saveNotes(@RequestParam("title") String title,
			               @RequestParam("professor") String professor,
			               @RequestParam("classCode") String classCode,
			               @RequestParam("description") String description,
			               @RequestParam("note") MultipartFile file,
			               @RequestParam("std")String std
			                )
	 {
		Notes theNote=new Notes();
		theNote.setTitle(title);
		theNote.setProfessor(professor);
		theNote.setClassCode(classCode);
		theNote.setDescription(description);
		theNote.setIdentifier(file.getOriginalFilename());
	/*	try {
			theNote.setNotesContent(file.getBytes());
		}
		catch(Exception e)
		{System.out.println(e.toString());}*/
		Notes note=notesRepository.save(theNote);	
		if(file.isEmpty())
		{
			throw new RuntimeException();
		}
		String path=classCode+"_"+note.getId()+"_";
		try {
		  
           // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            FileOutputStream fos = new FileOutputStream(path + file.getOriginalFilename());
            fos.write(bytes);
      	    fos.flush();
      	    fos.close();
            Notification notification=new Notification();
            notification.setMessage(description);
            notification.setTitle(title);
            notification.setNotificationDate(""+java.time.LocalDate.now());
            notification.setNotificationTime(""+java.time.LocalTime.now());
            notification.setStd(std);
            NotificationRepository.save(notification);
            

        } catch (IOException e) {
        	throw new RuntimeException(e.getMessage());
        }
		return note;
	}
	@DeleteMapping("/notes/{id}")
	public Successresponse deleteNotes(@PathVariable int id)
	{
		Notes note=notesRepository.getOne(id);
		if(note==null)
		{
		  throw new RuntimeException("No notes found");
		}
		notesRepository.delete(note);
		Successresponse reponseObject=new Successresponse();
		reponseObject.setMessage("notes deleted successfully");
	    return reponseObject;
	}
	@GetMapping("/notes/download/{id}")
	public ResponseEntity<Resource> downloadAssignment(@PathVariable int id,HttpServletRequest request)
	{
		Notes note=notesRepository.getOne(id);
		String contentType=null;
		Resource resource=null;
		if(note==null)
		{
		  throw new RuntimeException("No notes found");
		}
		String path=note.getClassCode()+"_"+note.getId()+"_";
		Path newPath = Paths.get(path + note.getIdentifier());
		try {
			resource = new UrlResource(newPath.toUri());
			if(resource.exists()) {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } else {
                throw new RuntimeException("File not found ");
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(""+e.getMessage());
		}
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
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
