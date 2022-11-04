//package com.perfios.controller;
//
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.perfios.model.Student;
//import com.perfios.service.ExamScheduleService;
//import com.perfios.service.StudentService;
//import com.perfios.service.imp.UserServiceImpl;
//
//@Controller
//public class ExamScheduleController {
//
//	private ExamScheduleService examScheduleService;
//	public ExamScheduleController(ExamScheduleService examScheduleService) {
//		super();
//		this.examScheduleService = examScheduleService;
//	}
//	@GetMapping("/student")
//	public String homePage(Model m) {
//		List<Student> students = examScheduleService.list(m)
//		m.addAttribute("students", students);
//		return "success";
//	}
//}
