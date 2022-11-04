package com.perfios.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.perfios.dto.UserRegistrationDto;
import com.perfios.model.User;




public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

//	UserDetails loadUserByUsernameandemail(String email, String password);

	
}