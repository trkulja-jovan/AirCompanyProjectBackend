package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class SearchFlightDto {
	
	private Integer idAerodromOd;
	private Integer idAerodromDo;
	private Date datumPolaska;
	private Date datumPovratka;

}
