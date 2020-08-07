package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AirportDto {
	
	private int idAerodrom;
	private String code;
	private String drzava;
	private String grad;

}
