package com.airline.mapper;

@FunctionalInterface
public interface IMapper<S, D>{
	
	D map(S source, Class<D> destination);

}
