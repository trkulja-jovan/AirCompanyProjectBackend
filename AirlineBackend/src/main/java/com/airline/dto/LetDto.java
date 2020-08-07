package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LetDto {
	
	Integer idLet;
	String oznakaLeta;
    AviokompanijaDto aviokompanija;
	PodaciLetaDto podaciLeta;

}
