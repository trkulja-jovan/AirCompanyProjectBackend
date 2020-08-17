package com.airline.interfaces.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.FullUserDto;
import com.airline.dto.TokenDto;
import com.airline.dto.UserDto;

@Service
public interface ILoginService {
	
	ResponseEntity<?> tryToLogin(UserDto data);
	ResponseEntity<?> registerUser(FullUserDto data);
	ResponseEntity<?> checkValidity(String data);
	ResponseEntity<?> logoutUser(TokenDto data);

}
