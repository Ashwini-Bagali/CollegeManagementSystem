package com.perfios.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.perfios.dto.UserRegistrationDto;
import com.perfios.model.Student;
import com.perfios.model.User;
import com.perfios.repository.UserRepository;
import com.perfios.service.StudentService;
import com.perfios.service.UserService;

@Controller
public class UserController {
	private UserService userService;
	private UserRepository userRepository;
	private StudentService studentService;

	// private BCryptPasswordEncoder passwordEncoder;
	public UserController(UserService userService, StudentService studentService) {
		super();
		this.userService = userService;
		this.studentService = studentService;
		// this.passwordEncoder=passwordEncoder;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {

		userService.save(registrationDto);
		return "redirect:/registration?success";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@PostMapping("/registersubmit")
	public String registersubmit(UserRegistrationDto user) {
		userService.save(user);

		return "login";
	}

	@RequestMapping(value = "/loginsubmit", method = RequestMethod.POST)
	public String verify(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request, Model model, Student student, HttpSession session) {
		System.out.println("Form data :" + email + password);

		UserDetails user = userService.loadUserByUsername(email);
		

//		if (s.toString().equals(s2.toString()) == true) {
//			System.out.println("wow");
//		} else
//			System.out.println("not wow");
		if (user != null) {
			Collection<? extends GrantedAuthority> s = user.getAuthorities();
			Collection<Object> s2 = new ArrayList();
			s2.add("ROLE_USER");
	//s.toString()
			System.out.println(s2.toString());
			System.out.println("ji" + s.toString().equals(s2.toString()));
			System.out.println(s2);
			System.out.println(s);
			System.out.println("idk" + user.getAuthorities().equals(s2));
			if (s.toString().equals(s2.toString()) == true) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

				if (encoder.matches(password, user.getPassword())) {
					/*
					 * List<Student> listStd = studentService.showAllDetails(); List<Student>
					 * students = studentService.getAllStudents(); model.addAttribute("students",
					 * students); model.addAttribute("msg", "success login");
					 * System.out.println("success");
					 */
					return "redirect:/getExamSch";

				} else {
					System.out.println("password missmatch");
					model.addAttribute("msg", "UserEmail and password do not match..");
					session.setAttribute("msg", "UserEmail and password do not match..");
				}
			}
			else {
				List<Student> listStd = studentService.showAllDetails();
				List<Student> students = studentService.getAllStudents();
				model.addAttribute("students", students);
				return "admin";
			}
		}
		else {
			System.out.println("user not found");
			session.setAttribute("msg", "UserEmail not found..");
			return "login";
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
		return "login";
	}
}