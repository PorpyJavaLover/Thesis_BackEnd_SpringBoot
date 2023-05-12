package com.thesis.scheduling.modellevel.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Plan;
import com.thesis.scheduling.modellevel.repository.PlanRepository;

@Service
public class PlanService {

	private final PlanRepository repository;

	public PlanService(PlanRepository repository) {
		this.repository = repository;
	}

	// GET
	public Iterable<Plan> findAll() {
		return repository.findAll();
	}

	public Iterable<Plan> findAllBySemester(int semester) {
		return repository.findAllBySemester(semester);
	}

	public Iterable<Plan> findAllBySemesterAndGroupId(int semester, Group groupId) {
		return repository.findAllBySemesterAndGroupId(semester, groupId);
	}

	public Iterable<Plan> findAllByYearsAndSemester(int years , int semester) {
		return repository.findAllByYearsAndSemester(years , semester);
	}

	// SET
	public Plan createStaff(int years,int semester,Course courseId,Group groupId) {
		Plan entity = new Plan();
		
		Optional<Plan> opt = repository.findByYearsAndSemesterAndCourseIdAndGroupId(years, semester, courseId, groupId);
		
		if (opt.isPresent()) {
			entity = opt.get();

		} else {
			entity.setYears(years);
			entity.setSemester(semester);
			entity.setCourseId(courseId);
			entity.setGroupId(groupId);
			repository.save(entity);
		}
		
		return entity;
	}

	// DETELE
	public void delete(int years,int semester,Course courseId,Group groupId) {

		Plan entity = new Plan();

		Optional<Plan> opt = repository.findByYearsAndSemesterAndCourseIdAndGroupId(years, semester, courseId, groupId);

		if ( opt.isPresent()) {
			entity = opt.get();
			repository.delete(entity);
		}

	}

}
