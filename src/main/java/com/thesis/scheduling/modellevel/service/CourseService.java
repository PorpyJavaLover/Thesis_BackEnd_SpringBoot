package com.thesis.scheduling.modellevel.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.repository.CourseRepository;

@Service
public class CourseService {
	
	 private final CourseRepository repository;

	    public CourseService(CourseRepository repository) {
	        this.repository = repository;
	    }
	    
	    public Optional<Course> findByCourseId(Long cId){
	    	return repository.findByCourseId(cId);
	    }
}
