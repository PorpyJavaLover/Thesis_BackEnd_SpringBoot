package com.thesis.scheduling.modellevel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Plan;

@Repository
public interface PlanRepository extends CrudRepository<Plan,String>{
	
	Iterable<Plan> findByGroupId(Group groupId);

	Optional<Plan> findByYearsAndSemesterAndCourseIdAndGroupId(int years , int semester , Course courseId , Group groupId );
	
}
