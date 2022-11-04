package com.perfios.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="student")
public class Student {
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", phno=" + phno + ", course=" + course
				+ "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
private int id;
	
private String name;
private String email;
private String phno;
private String course;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhno() {
	return phno;
}
public void setPhno(String phno) {
	this.phno = phno;
}
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}
}
