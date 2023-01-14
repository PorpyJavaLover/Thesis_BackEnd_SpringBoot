package com.thesis.scheduling.modellevel.mapper;

import org.mapstruct.Mapper;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_ShowAllTeacher_Response;

@Mapper(componentModel = "spring")
public interface LeaveTeachMapper {
	
	Iterable<M_LeaveTeach_ShowAllTeacher_Response> toMShowAllTeacher(Iterable<LeaveTeach> leaveTeach);
	
}
