package com.thesis.scheduling.modellevel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, String> {
	
    Optional<Group> findByGroupId(Long gId);
}
