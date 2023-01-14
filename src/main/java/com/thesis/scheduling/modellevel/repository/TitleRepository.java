package com.thesis.scheduling.modellevel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Title;

@Repository
public interface TitleRepository extends CrudRepository<Title, String> {
	
    Optional<Title> findByTitleId(Long id);
    
    
}
