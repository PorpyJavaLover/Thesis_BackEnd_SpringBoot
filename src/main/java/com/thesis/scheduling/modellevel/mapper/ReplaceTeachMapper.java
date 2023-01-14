package com.thesis.scheduling.modellevel.mapper;

import org.mapstruct.Mapper;

import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_ShowAllTeacher_Response;

@Mapper(componentModel = "spring")
public interface ReplaceTeachMapper {

	Iterable<M_ReplaceTeach_ShowAllTeacher_Response> toMShowAllTeacher(Iterable<ReplaceTeach> replaceTeach);
	
}
