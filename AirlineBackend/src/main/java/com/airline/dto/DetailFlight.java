package com.airline.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DetailFlight {
	
	
	private List<UslugaDto> usluge;
	private Double cena;
	private KlasaDto klasa;

}
