package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class KartaDto {
	
	private Integer idKarta;
	private String brojKarte;
	private Double cena;
	private LetDto let;

}
