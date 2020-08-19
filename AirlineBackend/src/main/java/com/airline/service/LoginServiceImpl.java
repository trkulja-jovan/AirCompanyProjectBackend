package com.airline.service;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.airline.dto.FullUserDto;
import com.airline.dto.TokenDto;
import com.airline.dto.UserDto;
import com.airline.interfaces.service.ILoginService;
import com.airline.mapper.IMapper;

import AirlineJPA.Korisnik;

@Service
@SuppressWarnings("unchecked")
class LoginServiceImpl extends BaseService implements ILoginService {
	
	@Autowired
	private @Lazy IMapper<FullUserDto, Korisnik> _mapper;
	
	@Override
	public ResponseEntity<TokenDto> tryToLogin(UserDto user) {
		
		if(super.existsUserByPassword(user.getUsername(), user.getPassword())) {
			var token = BCrypt.gensalt(31);
			
			var tokenData = new TokenDto();
			tokenData.setToken(token);
			tokenData.setUser(user);
			
			super.addUser(user.getUsername(), token);
			
			return ok(tokenData);
		}
		
		return badRequest();
	}
	
	@Override
	public ResponseEntity<Boolean> registerUser(FullUserDto data) {
		
		try {
			
			var user = _mapper.map(data, Korisnik.class);
			
			userRep.save(user);
			
			return ok(true);
			
		} catch(Exception e) {
			return ResponseEntity.ok(false);
		}
	}
	
	@Override
	public ResponseEntity<Boolean> checkValidity(String body){
		
		var p = new JSONParser(body);
		
		try {
			var username = (String) p.object().get("username");
			
			return ok(existsUser(username));
			
		} catch(ParseException e) {
			return ResponseEntity.ok(false);
		}
		
	}
	
	@Override
	public ResponseEntity<Boolean> logoutUser(TokenDto data){
		
		try {
			var username = data.getUser().getUsername();
			var token = data.getToken();
			
			return ok(removeLoggedUser(username, token));
		} catch(Exception e) {
			return badRequest();
		}
	}
}
