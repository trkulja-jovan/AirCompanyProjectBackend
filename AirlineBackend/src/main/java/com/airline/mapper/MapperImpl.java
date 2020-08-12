package com.airline.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("_mapper")
class MapperImpl<S, D> implements IMapper<S, D> {
	
	private ObjectMapper _mapper = new ObjectMapper();
	
	@Override
	public D map(S source, Class<D> destination) {
		return _mapper.convertValue(source, destination);
	}
	
	@Override
	public D mapFromJson(String json, Class<D> destination) {
		try {
			return _mapper.readValue(json, destination);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
