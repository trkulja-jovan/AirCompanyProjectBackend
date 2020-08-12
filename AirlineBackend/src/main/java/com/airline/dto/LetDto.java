package com.airline.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LetDto {
	
	private Integer idLet;
	private String oznakaLeta;
	private Double cena;
	
	private List<KlasaDto> klasa;
    private AviokompanijaDto aviokompanija;
	private PodaciLetaDto podaciLeta;

}
