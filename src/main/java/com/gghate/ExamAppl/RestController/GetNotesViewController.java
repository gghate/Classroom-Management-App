package com.gghate.ExamAppl.RestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notes")
public class GetNotesViewController {

	@RequestMapping("/create")
	public String getNotesForm()
	{
		return "notes";
	}
}
