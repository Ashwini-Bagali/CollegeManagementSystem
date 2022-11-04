package com.perfios.service.imp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.perfios.exception.ResourceNotFoundException;
import com.perfios.model.Student;
import com.perfios.repository.StudentRepository;
import com.perfios.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {
	private StudentRepository repo;
	public StudentServiceImpl(StudentRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Student saveStudent(Student s) {
		return repo.save(s);
	}
	public Student get(int id) {
		return repo.findById(id).get();
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	@Override
	public List<Student> getAllStudents() {
		return repo.findAll();
	}
	@Override
	public Student getStudentById(Integer id) throws ResourceNotFoundException {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Not Found"));
	}
	@Override
	public Student updateStudent(Student student, Integer id) throws ResourceNotFoundException {
		Student existingStudent = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Available"));
		existingStudent.setName(student.getName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setCourse(student.getCourse());
		existingStudent.setPhno(student.getPhno());
		repo.save(existingStudent);
		return existingStudent;
	}
	@Override
	public void deleteStudent(Integer id) throws ResourceNotFoundException {
		Student existingStudent = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Available"));
		repo.deleteById((int) id);
	}
	
	public List<Student> showAllDetails()
	{
		List<Student> detaillist = new ArrayList<>();
		
		for(Student d:repo.findAll())
		{
			detaillist.add(d);
		}
		
		
		return detaillist;
	}
	public List<Student> searchResult(String name)
	{
		List<Student> detaillist = new ArrayList<>();
		
	detaillist= repo.getUserByUsername(name);
		
		
		return detaillist;
	}
	@Override
	public List<Student> orderByName(String name) {
		if(name != null) {
			return repo.orderedList(name);
		}
	return	repo.findAll(Sort.by(Sort.Direction.ASC, "name"));

	}
	@Override
	public List<Student> orderByNameDesc(String name) {
		if(name != null) {
			return repo.orderedList(name);
		}
	return	repo.findAll(Sort.by(Sort.Direction.DESC, "name"));

	}
	@Override
	 public List<Student> listAll(String keyword) {
	        if (keyword != null) {
	            return repo.search(keyword);
	        }
	        return repo.findAll();
	    }
	@Override
	 public List<Student> list(String keyword) {
	        if (keyword != null) {
	            return repo.searchByName(keyword);
	        }
	        return repo.findAll();
	    }

}