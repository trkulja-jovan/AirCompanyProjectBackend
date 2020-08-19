package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Rezervacija {
	
	private Integer idSediste;
	private Integer idLet;
	private Double cena;
	private String token;

}
