package com.thesis.scheduling.businesslevel.logic;

import java.util.ArrayList;
import java.util.Collection;

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

import javafx.print.Collation;

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

	public Iterable<M_Plan_ShowAllStaff_Response> showAllStaff(int years , int semester) {

		Iterable<Plan> sourceA = planService.findAllBySemester(semester);

		Collection<Plan> sourceB = new ArrayList<Plan>();

		for(Plan sourceATmp : sourceA ){
			int i = Integer.parseInt(sourceATmp.getYears().toString()) - 1;
			int j = Integer.parseInt(sourceATmp.getGroupId().getGroup_name().substring(
				sourceATmp.getGroupId().getGroup_name().indexOf('.') + 1,
				sourceATmp.getGroupId().getGroup_name().indexOf('.') + 3));
				if( Integer.parseInt("25" + (j + i)) == (years + 543)){
					sourceB.add(sourceATmp);
				}
		}
		
		return mapper.toMTableResponseStaff(sourceB);
	}

	// SET
	public Plan createStaff(@RequestBody M_Plan_CreateTeacher_Request request) {
		return planService.createStaff(request.getSemester(), request.getYears(), request.getCourseId(),
				request.getGroupId());
	}

	// DELETE
	public void delete(int years, int semester, Course courseId, Group groupId) {
		planService.delete(years, semester, courseId, groupId);
	}
}
