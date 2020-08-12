package com.airline.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PodaciLetaDto {
	
	private Integer idPodaci;
	private AerodromDto aerodrom1;
	private AerodromDto aerodrom2;

}
