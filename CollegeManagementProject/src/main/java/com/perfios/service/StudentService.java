package com.perfios.service;

import java.util.List;

import com.perfios.exception.ResourceNotFoundException;
import com.perfios.model.Student;

public interface StudentService {
	Student saveStudent(Student student);
	List<Student> getAllStudents();
	Student getStudentById(Integer id) throws ResourceNotFoundException;
	Student updateStudent(Student student, Integer id) throws ResourceNotFoundException;
	void deleteStudent(Integer id) throws ResourceNotFoundException;
	List<Student>showAllDetails();
	List<Student> searchResult(String name);
	List<Student> orderByName(String name);
	List<Student> listAll(String keyword);
	List<Student> orderByNameDesc(String name);
	List<Student> list(String keyword);
}
