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
			///@note Y2K in year 2600
			int i = Integer.parseInt(sourceTmp.getYears().toString()) - 1;
			int j = Integer.parseInt(sourceTmp.getGroupId().getGroup_name().substring(
					sourceTmp.getGroupId().getGroup_name().indexOf('.') + 1,
					sourceTmp.getGroupId().getGroup_name().indexOf('.') + 3));

			targetSub.setYears(sourceTmp.getYears().toString());
			targetSub.setYears_value((Integer.parseInt(("25" + (j + i)))-543)+"");
			targetSub.setYears_name("25" + (j + i));
			targetSub.setSemester(sourceTmp.getSemester().toString());
			targetSub.setCourse_id(sourceTmp.getCourseId().getCourseId().toString());
			targetSub.setCourse_code(sourceTmp.getCourseId().getCourse_code().toString());
			targetSub.setCourse_title(sourceTmp.getCourseId().getCourse_title().toString());
			targetSub.setGroup_id(sourceTmp.getGroupId().getGroupId().toString());
			targetSub.setGroup_name(sourceTmp.getGroupId().getGroup_name().toString());
			targetSub.setSelected_lect(false);
			targetSub.setSelected_perf(false);
			targetSub.setDisable_lect((sourceTmp.getCourseId().getCourseLect() == 0 ? true : false));
			targetSub.setDisable_perf((sourceTmp.getCourseId().getCoursePerf() == 0 ? true : false));
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
			//@note Y2K in year 2600
			int i = Integer.parseInt(sourceTmp.getYears().toString()) - 1;
			int j = Integer.parseInt(sourceTmp.getGroupId().getGroup_name().substring(
					sourceTmp.getGroupId().getGroup_name().indexOf('.') + 1,
					sourceTmp.getGroupId().getGroup_name().indexOf('.') + 3));

			targetSub.setYears(sourceTmp.getYears().toString());
			targetSub.setYears_value((Integer.parseInt(("25" + (j + i)))-543)+"");
			targetSub.setYears_name("25" + (j + i));
			targetSub.setSemester(sourceTmp.getSemester().toString());
			targetSub.setCourse_id(sourceTmp.getCourseId().getCourseId().toString());
			targetSub.setCourse_code(sourceTmp.getCourseId().getCourse_code().toString());
			targetSub.setCourse_title(sourceTmp.getCourseId().getCourse_title().toString());
			targetSub.setGroup_id(sourceTmp.getGroupId().getGroupId().toString());
			targetSub.setGroup_name(sourceTmp.getGroupId().getGroup_name().toString());
			targetSub.setSelected_lect(false);
			targetSub.setSelected_perf(false);
			targetSub.setDisable_lect((sourceTmp.getCourseId().getCourseLect() == 0 ? true : false));
			targetSub.setDisable_perf((sourceTmp.getCourseId().getCoursePerf() == 0 ? true : false));
			target.add(targetSub);
		}

		return target;
	}

}
