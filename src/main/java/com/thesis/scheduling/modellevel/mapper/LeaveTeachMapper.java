package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_ShowAllTeacher_Response;

@Component
public class LeaveTeachMapper {
	
	public Iterable<M_LeaveTeach_ShowAllTeacher_Response> toMShowAllTeacher(Iterable<LeaveTeach> source){

		if (source == null) {
			return null;
		}

		Collection<M_LeaveTeach_ShowAllTeacher_Response> target = new ArrayList<M_LeaveTeach_ShowAllTeacher_Response>();

		for (LeaveTeach sourceTmp : source) {
			M_LeaveTeach_ShowAllTeacher_Response targetSub = new M_LeaveTeach_ShowAllTeacher_Response();
			targetSub.setLeaveTeachId(sourceTmp.getLeaveTeachId());
			targetSub.setYears(String.valueOf(Integer.parseInt(sourceTmp.getYears().toString()) + 543));
			targetSub.setSemester(sourceTmp.getSemester().toString());
			targetSub.setDateStart(new Date(sourceTmp.getDateStart().getYear() + 543,sourceTmp.getDateStart().getMonth(), sourceTmp.getDateStart().getDate()));
			targetSub.setDateEnd(new Date(sourceTmp.getDateEnd().getYear() + 543,sourceTmp.getDateEnd().getMonth(), sourceTmp.getDateEnd().getDate()));
			targetSub.setNote(sourceTmp.getNote());
			target.add(targetSub);
		}

		return target;

	}

	
}
