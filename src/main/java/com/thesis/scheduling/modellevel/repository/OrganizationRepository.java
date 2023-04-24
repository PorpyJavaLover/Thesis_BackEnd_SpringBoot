package com.thesis.scheduling.modellevel.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {

	Iterable<Organization> findByTypeAndParentIn(int type , Collection<String> text);

	Collection<Organization> findByCodeStartingWithAndNameStartingWithAndType( String code , String name , int type);

	Optional<Organization> findByCode(String code);
	
}
