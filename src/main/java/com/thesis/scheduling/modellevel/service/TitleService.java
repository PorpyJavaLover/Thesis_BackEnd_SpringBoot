package com.thesis.scheduling.modellevel.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Title;
import com.thesis.scheduling.modellevel.repository.TitleRepository;

@Service
public class TitleService {

	private final TitleRepository repository;

	public TitleService(TitleRepository repository) {
		this.repository = repository;
	}

	// GET
	public Iterable<Title> showAll() {
		return repository.findAll();
	}

	public Optional<Title> findByTitleId(Long titleId) {
		return repository.findByTitleId(titleId);
	}

	// SET
	public void createStaff(String titleName, String titleShort) {

		// validate ??

		// save
		Title entity = new Title();
		entity.setTitleName(titleName);
		entity.setTitleShort(titleShort);
		repository.save(entity);

	}
	
	//DELETE
	public void delete(Long titleId) {

		Title entity = new Title();

		Optional<Title> opt = repository.findByTitleId(titleId);

		if (!opt.isEmpty()) {
			entity = opt.get();
			repository.delete(entity);
		}

	}

}
