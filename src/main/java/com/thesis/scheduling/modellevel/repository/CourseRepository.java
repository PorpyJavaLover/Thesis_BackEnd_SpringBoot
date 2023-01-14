package com.thesis.scheduling.modellevel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    Optional<Course> findByCourseId(Long cId);
}
