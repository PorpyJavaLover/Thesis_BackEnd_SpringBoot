package com.thesis.scheduling.modellevel.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.repository.GroupRepository;

@Service
public class GroupService {

    private final GroupRepository repository;

    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }
    
    public Optional<Group> findByGroupId(Long gId){
    	return repository.findByGroupId(gId);
    }
}
