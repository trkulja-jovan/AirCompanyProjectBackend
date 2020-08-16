package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LetDto {
	
	private Integer idLet;
	private String oznakaLeta;
	
    private AviokompanijaDto aviokompanija;
	private PodaciLetaDto podaciLeta;

}
