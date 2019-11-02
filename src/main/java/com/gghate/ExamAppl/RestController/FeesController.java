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

import com.gghate.ExamAppl.Entity.FeeRecord;
import com.gghate.ExamAppl.Response.CustomError;
import com.gghate.ExamAppl.Response.Successresponse;
import com.gghate.ExamAppl.Service.FeeRecordRepository;

@RestController
@RequestMapping("/api")
public class FeesController {
	
	@Autowired
	private FeeRecordRepository feeRecord;
	
	@PostMapping("/fee")
	public FeeRecord saveFeeRecord(@RequestBody FeeRecord theFeeRecord)
	{
		theFeeRecord.setId(0);
		FeeRecord tempFeeRecord=feeRecord.save(theFeeRecord);
		return tempFeeRecord;
	}

	@DeleteMapping("/fee/{id}")
	public Successresponse deleteFeeRecord(@PathVariable int id)
	{
		if(feeRecord.findById(id)==null)
		{
			throw new RuntimeException("No Record found");
		}
		
		feeRecord.deleteById(id);
		Successresponse reponseObject=new Successresponse();
		reponseObject.setMessage("Fee record deleted successfully");
	    return reponseObject;
		
	}
	
	@PutMapping("/fee")
	public FeeRecord updateFeeRecord(@RequestBody FeeRecord theFeeRecord)
	{
		FeeRecord tempFeeRecord=feeRecord.save(theFeeRecord);
		return tempFeeRecord;
		
	}
	
	@GetMapping("/fee/{studentid}")
	public List<FeeRecord> getFeeRecords(@PathVariable int studentid)
	{
		return feeRecord.findByStudentid(studentid);
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomError> handleException(Exception exc)
	{
		CustomError customError=new CustomError();
		customError.setTitle("Exception");
		customError.setMessage(exc.getMessage());
		return new ResponseEntity<>(customError,HttpStatus.OK);
	}
	
}
