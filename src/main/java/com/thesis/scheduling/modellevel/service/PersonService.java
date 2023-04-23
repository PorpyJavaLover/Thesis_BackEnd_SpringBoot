package com.thesis.scheduling.modellevel.service;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
		this.repository = repository;
	}
    
    //GET
    

    //SET

    //DELETE
}
