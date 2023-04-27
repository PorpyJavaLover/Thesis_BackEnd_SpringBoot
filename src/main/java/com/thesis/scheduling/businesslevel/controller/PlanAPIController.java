package com.thesis.scheduling.businesslevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.logic.PlanLogic;
import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Plan;
import com.thesis.scheduling.modellevel.model.M_Plan_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_Plan_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Plan_ShowAllTeacher_Response;

@RestController
@RequestMapping("/plan")
public class PlanAPIController {

	private final PlanLogic planLogic;

	public PlanAPIController(PlanLogic planlogic) {
		this.planLogic = planlogic;
	}

	// GET
	@GetMapping("/teacher/show/all/{years}/{semester}")
	public ResponseEntity<Iterable<M_Plan_ShowAllStaff_Response>> showAllTeacher(@PathVariable("years") int years, @PathVariable("semester") int semester) throws BaseException {
		Iterable<M_Plan_ShowAllStaff_Response> response = planLogic.showAllStaff(years, semester);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/all/{years}/{semester}")
	public ResponseEntity<Iterable<M_Plan_ShowAllStaff_Response>> showAllStaff(@PathVariable("years") int years, @PathVariable("semester") int semester) throws BaseException {
		Iterable<M_Plan_ShowAllStaff_Response> response = planLogic.showAllStaff(years, semester);
		return ResponseEntity.ok(response);
	}

	// SET
	@PostMapping("/staff/create")
	public ResponseEntity<Plan> createStaff(@RequestBody M_Plan_CreateTeacher_Request request) throws BaseException {
		return ResponseEntity.ok(planLogic.createStaff(request));
	}

	// DELETE
	@DeleteMapping("/teacher/delete/{years}/{semester}/{courseId}/{groupId}")
	public void delete(@PathVariable("years") int years, @PathVariable("semester") int semester,
			@PathVariable("courseId") Course courseId, @PathVariable("groupId") Group groupId)
			throws BaseException {
		planLogic.delete(years, semester, courseId, groupId);
	}
}
