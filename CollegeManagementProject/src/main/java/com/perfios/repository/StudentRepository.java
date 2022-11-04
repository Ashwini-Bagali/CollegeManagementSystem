package com.perfios.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.perfios.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Optional<Student> findById(long id);

	
	@Query("SELECT s FROM Student s WHERE s.name = :name")
	  public List<Student> getUserByUsername(@Param("name") String name);
	
	@Query("SELECT v FROM Student v ORDER BY v.name ")
	public List<Student> orderedList(@Param("name") String name);
	
	@Query("SELECT p FROM Student p WHERE CONCAT(p.id, p.name, p.email, p.course, p.phno) LIKE %?1%")
	public List<Student> search(String keyword);
	
	@Query("SELECT n FROM Student n WHERE  n.name LIKE %?1%")
	public List<Student> searchByName(String keyword);
}
