package com.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.interfaces.service.ILetService;

@RestController
@RequestMapping("/api/flights")
public class LetController<LetDto> {
	
	@Autowired
	ILetService<LetDto> service;

}
