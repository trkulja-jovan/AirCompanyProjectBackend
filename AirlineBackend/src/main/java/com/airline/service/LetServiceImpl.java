package com.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.dto.LetDto;
import com.airline.interfaces.service.ILetService;
import com.airline.repository.LetRepository;

@Service
public class LetServiceImpl implements ILetService<LetDto>{
	
	@Autowired
	private LetRepository repositoryLet;
	
}
