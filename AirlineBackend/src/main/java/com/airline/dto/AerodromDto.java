package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AerodromDto {
	
	private int idAerodrom;
	private String code;
	private String drzava;
	private String grad;

	
}