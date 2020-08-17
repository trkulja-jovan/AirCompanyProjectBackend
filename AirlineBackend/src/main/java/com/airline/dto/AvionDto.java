package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AvionDto {
	
	private int idAvion;
	private int maxBrojSedista;
	private String serijskiBroj;
	private String tipAviona;

}
