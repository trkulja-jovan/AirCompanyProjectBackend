package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AviokompanijaDto {
	
	private Integer idAviokompanija;
	private String nazivKompanije;
	private String tipKompanije;
}
