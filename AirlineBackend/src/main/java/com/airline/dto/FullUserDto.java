package com.airline.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FullUserDto {
	
	private String brojTelefona;
	private Date godinaRodjenja;
	private String ime;
	private String jmbg;
	private String prezime;
	private UserDto logindata;

}
