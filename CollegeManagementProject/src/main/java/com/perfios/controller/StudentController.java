package com.perfios.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.perfios.EmailSenderService;
import com.perfios.exception.ResourceNotFoundException;
import com.perfios.model.ExamSchedule;
import com.perfios.model.Student;
import com.perfios.service.ExamScheduleService;
import com.perfios.service.StudentService;
import com.perfios.service.imp.UserServiceImpl;
@Controller
public class StudentController {
	private StudentService studentService;
	private UserServiceImpl userServiceImpl;
	private ExamScheduleService examScheduleService;
	private EmailSenderService emailSenderService;
	public StudentController(StudentService studentService,UserServiceImpl userServiceImpl,ExamScheduleService examScheduleService,EmailSenderService emailSenderService) {
		super();
		this.studentService = studentService;
		this.userServiceImpl=userServiceImpl;
		this.examScheduleService=examScheduleService;
		this. emailSenderService= emailSenderService;
	}
	
//	@PostMapping("/saveStudent")
//	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
//		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
//	}
//	@GetMapping("/students")
//	public List<Student> getStudents() {
//		return studentService.getAllStudents();
//	}
//	
//	@GetMapping("/Students/{id}")
//	public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer StudentId) {
//		return new ResponseEntity<Student>(studentService.getStudentById(StudentId), HttpStatus.OK);	
//	}
//	
//	@PutMapping("/Students/updateStudent/{id}")
//	public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
//		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/Students/deleteStudent/{id}")
//	public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
//		studentService.deleteStudent(id);
//		return new ResponseEntity<String>("Student deleted successfully", HttpStatus.OK);
//	}
//	@GetMapping("/hello")
//	public ModelAndView homePage(HttpServletRequest request,ModelAndView model ,Student student)
//	{
//		
//		List<Student> listStd = studentService.showAllDetails();
//		model.addObject("listStd", listStd);
//		model.setViewName("success");
//		return model;
//	}
//	@GetMapping("/search/{name}")
//	public ModelAndView search(HttpServletRequest request,ModelAndView model ,@PathVariable("name") String name)
//	{
//		
//		List<Student> listStd = studentService.searchResult(name);
//		model.addObject("result", listStd);
//		model.setViewName("success");
//		return model;
//	}

	
//	@GetMapping("/search/{name}")
//	public ModelAndView search(HttpServletRequest request, ModelAndView model, @PathVariable("name") String name) {
//		List<Student> listStd = studentService.searchResult(name);
//		System.out.println(listStd+name);
//		model.addObject("searchKey",name);
//		model.addObject("keyword", listStd);
//		model.setViewName("search");
//		return model;
//	}
	@GetMapping("/student")
	public String homePage(Model m) {
		List<Student> students = studentService.getAllStudents();
		m.addAttribute("students", students);
		return "success";
	}
	@GetMapping("/adminpage")
	public String homePageAdmin(Model m) {
		List<Student> students = studentService.getAllStudents();
		m.addAttribute("students", students);
		return "admin";
	}
	@PostMapping("/savestudent")
	public String saveStudent(Student student,Model m) {
		studentService.saveStudent(student);
		List<Student> students = studentService.getAllStudents();
		m.addAttribute("students", students);
		return "admin";
	}
	@GetMapping("/addstudent")
	public String newStudentPage(Model model, HttpSession session) {
		Student student = new Student();
		model.addAttribute(student);
		session.setAttribute("msg", "Student Data Added Successfully...");
		return "new_student";
	}
	@GetMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable int id, Model model) throws ResourceNotFoundException {
		Student s = studentService.getStudentById(id);
		model.addAttribute("student", s);
		return "edit";
	}
	@PostMapping("/update")
	public String updateStd(@ModelAttribute Student s, HttpSession session,Model m) {
		studentService.saveStudent(s);
		System.out.println(s.getName());
		List<Student> students = studentService.getAllStudents();
		m.addAttribute("students", students);
		session.setAttribute("msg", "Student Data Updated Sucessfully..");
		System.out.println("email"+s.getEmail());
		 emailSenderService.sendSimpleEmail(s.getEmail(), "Data Updated By the User", "Data Updated");
		return "redirect:/adminpage";
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable int id, HttpSession session,Model m) throws ResourceNotFoundException {
		studentService.deleteStudent(id);
		List<Student> students = studentService.getAllStudents();
		m.addAttribute("students", students);
		session.setAttribute("msg", "Student Data Delete Sucessfully..");
		return "redirect:/adminpage";
	}
	@GetMapping("/orderByName")
	public String orderByYear(String name, Model model) {
		List<Student> students = studentService.orderByName(name);
		// vehicleService.orderVehicles(year);
		model.addAttribute("students", students);
		System.out.println(students);
		return "success";

	}
	@GetMapping("/orderByNameDesc")
	public String orderByNameDesc(String name, Model model) {
		List<Student> students = studentService.orderByNameDesc(name);
		// vehicleService.orderVehicles(year);
		model.addAttribute("students", students);
		System.out.println(students);
		return "success";

	}
    @RequestMapping("/")
    public String SearchInUser(Model model, @Param("keyword") String keyword) {
        List<Student> listProducts = studentService.listAll(keyword);
        System.out.println("list"+listProducts);
        model.addAttribute("students", listProducts);
        model.addAttribute("keyword", keyword);
         
        return "success";
    }
    @RequestMapping("/search")
    public String SearchInAdmin(Model model, @Param("keyword") String keyword) {
        List<Student> listProducts = studentService.listAll(keyword);
        System.out.println("list"+listProducts);
        model.addAttribute("students", listProducts);
        model.addAttribute("keyword", keyword);
         
        return "admin";
    }
    @RequestMapping("/getExamSch")
    public String viewSchedule(Model model) {
    	String keyword=userServiceImpl.username;
        List<Student> listProducts = studentService.list(keyword);
       System.out.println("hi...."+listProducts.toString());
      List<ExamSchedule> list = null;
       
       
      for(Student std:listProducts) {
//    	String course=std.getCourse();
//    	System.out.println("course :"+course);
//    	ExamSchedule res=examScheduleService.list(course);
//    	System.out.println(res.toString());
//    	
//    	list.add(res);
    	// list.addAll(list);
    	//System.out.println("joker"+res.toString());
    	  
    	 System.out.println ("course"+examScheduleService.list(std.getCourse()));
    	  
      }
        System.out.println("list"+listProducts);
        model.addAttribute("students", listProducts);
       // model.addAttribute("keyword", keyword);
         
        return "success";
    }
    @RequestMapping("/timeTable")
    public String viewTTSchedule(Model model) {
    	String keyword=userServiceImpl.username;
        List<Student> listProducts = studentService.list(keyword);
       System.out.println("hi...."+listProducts.toString());
      List<ExamSchedule> list = new ArrayList<ExamSchedule>();
       
       
      for(Student std:listProducts) {
//    	String course=std.getCourse();
//    	System.out.println("course :"+course);
//    	ExamSchedule res=examScheduleService.list(course);
//    	System.out.println(res.toString());
//    	
//    	list.add(res);
    	// list.addAll(list);
    	//System.out.println("joker"+res.toString());
    	  list.add(examScheduleService.list(std.getCourse()));
    	 System.out.println ("course"+examScheduleService.list(std.getCourse()));
    	// model.addAttribute("tt",examScheduleService.list(std.getCourse()))
    	  
      }
        System.out.println("list"+list);
        model.addAttribute("tt", list);
       // model.addAttribute("keyword", keyword);
         
        return "timetable";
    }
}





