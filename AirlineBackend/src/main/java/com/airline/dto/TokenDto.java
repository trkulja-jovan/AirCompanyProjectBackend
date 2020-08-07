package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TokenDto {
	
	private String token;
	private UserDto user;

}
