package com.gghate.ExamAppl.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.gghate.ExamAppl.Entity.Notes;

@Service
public interface NotesRepository extends JpaRepository<Notes, Integer> {

	@Query("select n from Notes n where n.id>:id")
	public List<Notes> getNewNotes(int id);
	
	
	public List<Notes> findByClassCode(String classCode);
}
