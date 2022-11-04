package com.perfios.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.perfios.model.ExamSchedule;
import com.perfios.model.Student;
import com.perfios.repository.ExamScheduleRepository;
import com.perfios.repository.StudentRepository;
import com.perfios.service.ExamScheduleService;
@Service
public class ExamScheduleServiceImpl implements ExamScheduleService{
	private ExamScheduleRepository repo;
	public ExamScheduleServiceImpl(ExamScheduleRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public ExamSchedule list(String course) {
		return repo.findByCourse(course);
	}
}
