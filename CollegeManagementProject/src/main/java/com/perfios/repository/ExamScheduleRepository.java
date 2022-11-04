package com.perfios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfios.model.ExamSchedule;


public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Integer>{

    public ExamSchedule findByCourse(String course);

}
