package com.airline.mapper;

public interface IMapper<S, D>{
	
	D map(S source, Class<D> destination);
	D mapFromJson(String json, Class<D> destination);

}
