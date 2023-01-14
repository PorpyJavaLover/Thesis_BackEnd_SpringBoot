package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Plan;
import com.thesis.scheduling.modellevel.model.M_Plan_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Plan_ShowAllTeacher_Response;


@Component
public class PlanMapper {

	public Iterable<M_Plan_ShowAllTeacher_Response> toMTableResponseTeacher(Iterable<Plan> source) {
		if (source == null) {
			return null;
		}

		Collection<M_Plan_ShowAllTeacher_Response> target = new ArrayList<M_Plan_ShowAllTeacher_Response>();

		for (Plan sourceTmp : source) {
			M_Plan_ShowAllTeacher_Response targetSub = new M_Plan_ShowAllTeacher_Response();
			targetSub.setYears(sourceTmp.getYears().toString());
			targetSub.setSemester(sourceTmp.getSemester().toString());
			targetSub.setCourse_id(sourceTmp.getCourseId().getCourseId().toString());
			targetSub.setCourse_code(sourceTmp.getCourseId().getCourse_code().toString());
			targetSub.setCourse_title(sourceTmp.getCourseId().getCourse_title().toString());
			targetSub.setGroup_id(sourceTmp.getGroupId().getGroupId().toString());
			targetSub.setGroup_name(sourceTmp.getGroupId().getGroup_name().toString());
			targetSub.setSelected_lect(false);
			targetSub.setSelected_perf(false);
			target.add(targetSub);
		}
		return target;
		
	}

	public Iterable<M_Plan_ShowAllStaff_Response> toMTableResponseStaff(Iterable<Plan> source) {
		if (source == null) {
			return null;
		}
		
		Collection<M_Plan_ShowAllStaff_Response> target = new ArrayList<M_Plan_ShowAllStaff_Response>();

		for (Plan sourceTmp : source) {
			M_Plan_ShowAllStaff_Response targetSub = new M_Plan_ShowAllStaff_Response();
			targetSub.setYears(sourceTmp.getYears().toString());
			targetSub.setSemester(sourceTmp.getSemester().toString());
			targetSub.setCourse_id(sourceTmp.getCourseId().getCourseId().toString());
			targetSub.setCourse_code(sourceTmp.getCourseId().getCourse_code().toString());
			targetSub.setCourse_title(sourceTmp.getCourseId().getCourse_title().toString());
			targetSub.setGroup_id(sourceTmp.getGroupId().getGroupId().toString());
			targetSub.setGroup_name(sourceTmp.getGroupId().getGroup_name().toString());
			targetSub.setSelected_lect(false);
			targetSub.setSelected_perf(false);
			target.add(targetSub);
		}
		
		return target;
	}

}
