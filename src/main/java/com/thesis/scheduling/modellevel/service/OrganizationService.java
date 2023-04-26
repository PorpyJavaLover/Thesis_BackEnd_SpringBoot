package com.thesis.scheduling.modellevel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.repository.OrganizationRepository;

@Service
public class OrganizationService {

	private final OrganizationRepository repository;

	public OrganizationService(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Iterable<Organization> showAll() {
		return repository.findAll();
	}

	public Optional<Organization> findByCode(String sOrganize_code) {
		return repository.findByCode(sOrganize_code);
	}

	public Iterable<Organization> showSelect() {
		Collection<String> text = new ArrayList<String>();
		text.add("1830");
		text.add("1820");
		text.add("1750");
		text.add("1740");
		text.add("1730");
		text.add("1720");
		text.add("1630");
		text.add("1620");
		text.add("1530");
		text.add("1520");
		return repository.findByTypeAndParentIn(52, text);
	}

	public Collection<Organization> findByCodeStartingWithAndNameStartingWithAndType() {
		return repository.findAllByCodeStartingWithAndNameStartingWithAndType("1", "สาขา", 52);
	}

	public Collection<Organization> findAllByParent( String parent) {
		return repository.findAllByParent( parent );
	}

	public Collection<Organization> findAllByParentAndType( String parent , int type ) {
		return repository.findAllByParentAndType( parent, type );
	}

}
