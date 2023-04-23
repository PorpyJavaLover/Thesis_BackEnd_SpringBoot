package com.thesis.scheduling.modellevel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    
    
}
