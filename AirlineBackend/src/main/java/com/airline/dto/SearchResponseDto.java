package com.airline.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SearchResponseDto {
	
	private List<LetDto> polazniLetovi;
	private List<LetDto> povratniLetovi;

}
