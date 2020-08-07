package com.airline.interfaces.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.FullUserDto;
import com.airline.dto.TokenDto;
import com.airline.dto.UserDto;

@Service
public interface ILoginService {
	
	ResponseEntity<TokenDto> tryToLogin(UserDto data);
	ResponseEntity<Boolean> registerUser(FullUserDto data);
	ResponseEntity<Boolean> checkValidity(String username);

}
