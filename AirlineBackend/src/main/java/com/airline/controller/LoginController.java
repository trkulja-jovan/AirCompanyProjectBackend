package com.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.dto.FullUserDto;
import com.airline.dto.TokenDto;
import com.airline.dto.UserDto;
import com.airline.interfaces.service.ILoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	ILoginService loginService;

	@PostMapping("/loginUser")
	public ResponseEntity<?> login(@RequestBody UserDto user){
		return loginService.tryToLogin(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody FullUserDto fullUser){
		return loginService.registerUser(fullUser);
	}
	
	@PostMapping("/checkValidity")
	public ResponseEntity<?> checkValidityOfUsername(@RequestBody String value){
		return loginService.checkValidity(value);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser(@RequestBody TokenDto data){
		return loginService.logoutUser(data);
	}

}
