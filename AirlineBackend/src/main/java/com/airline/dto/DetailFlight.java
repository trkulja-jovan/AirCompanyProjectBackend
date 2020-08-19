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
	private List<KlasaDto> klasa;
	private List<SedisteDto> sedista;
	
	private Double cena;
	private Integer idLet;

}
