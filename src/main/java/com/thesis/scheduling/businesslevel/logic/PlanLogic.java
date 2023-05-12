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
import com.thesis.scheduling.modellevel.service.GroupService;
import com.thesis.scheduling.modellevel.service.PlanService;

@Service
public class PlanLogic {

	private final PlanMapper mapper;
	private final PlanService planService;
	private final GroupService groupService;


	public PlanLogic(PlanMapper mapper, PlanService planService, GroupService groupService) {
		this.mapper = mapper;
		this.planService = planService;
		this.groupService = groupService;
	}

	// GET
	public Iterable<M_Plan_ShowAllStaff_Response> showAll(int years , int semester, Long groupId) {

		Iterable<Plan> sourceA = planService.findAllBySemesterAndGroupId(semester, groupService.findByGroupId(groupId).get());

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
