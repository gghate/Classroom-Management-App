package com.gghate.ExamAppl.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notes")
public class Notes {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@Column(name="title")
	String title;
	@Column(name="professor")
	String professor;
	@Column(name="code")
	String classCode;
	@Column(name="Description")
	String description;
	@Column(name="identifier")
	String identifier;
/*	@Column(name="notes_content")
	byte[] notesContent;*/
	public  Notes() {
	}
	
/*	public byte[] getNotesContent() {
		return notesContent;
	}

	public void setNotesContent(byte[] notesContent) {
		this.notesContent = notesContent;
	}*/

	public int getId() {
		return id;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
