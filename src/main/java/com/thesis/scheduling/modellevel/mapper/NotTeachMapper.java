package com.thesis.scheduling.modellevel.mapper;

import org.mapstruct.Mapper;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.NotTeach;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_NotTeach_ShowAllTeacher_Response;

@Mapper(componentModel = "spring")
public interface NotTeachMapper {

	Iterable<M_NotTeach_ShowAllTeacher_Response> toMShowAllTeacher(Iterable<NotTeach> notTeach);
}