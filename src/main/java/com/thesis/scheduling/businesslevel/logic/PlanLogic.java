package com.thesis.scheduling.businesslevel.logic;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Plan;
import com.thesis.scheduling.modellevel.mapper.PlanMapper;
import com.thesis.scheduling.modellevel.model.M_Plan_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_Plan_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Plan_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.PlanService;

@Service
public class PlanLogic {

	private final PlanMapper mapper;
	private final PlanService planService;

	public PlanLogic(PlanService planservice, PlanMapper planmapper) {
		this.planService = planservice;
		this.mapper = planmapper;
	}

	// GET
	public Iterable<M_Plan_ShowAllTeacher_Response> showAllTeacher() {
		return mapper.toMTableResponseTeacher(planService.findAll());
	}

	public Iterable<M_Plan_ShowAllStaff_Response> showAllStaff() {
		return mapper.toMTableResponseStaff(planService.findAll());
	}

	// SET
	public Plan createStaff(@RequestBody M_Plan_CreateTeacher_Request request) {
		return planService.createStaff(request.getSemester(), request.getYears(), request.getCourseId(), request.getGroupId());
	}

	// DELETE
	public void delete(int years,int semester,Course courseId,Group groupId) {
		planService.delete(years,semester,courseId,groupId);
	}
}
