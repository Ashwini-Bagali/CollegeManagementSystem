package com.perfios.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="exam")
public class ExamSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String course;
private String examDate;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}
public String getExamDate() {
	return examDate;
}

@Override
public String toString() {
	return "ExamSchedule [id=" + id + ", course=" + course + ", examDate=" + examDate + "]";
}
public ExamSchedule() {
	super();
	// TODO Auto-generated constructor stub
}
public ExamSchedule(int id, String course, String examDate) {
	super();
	this.id = id;
	this.course = course;
	this.examDate = examDate;
}
public void setExamDate(String examDate) {
	this.examDate = examDate;
}
}
