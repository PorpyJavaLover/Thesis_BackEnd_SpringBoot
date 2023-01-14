package com.thesis.scheduling.modellevel.mapper;

import org.mapstruct.Mapper;

import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.model.M_Organization_ShowAllTeacher_Response;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

	Iterable<M_Organization_ShowAllTeacher_Response> toMShowAllTeacher(Iterable<Organization> organization);
	
}
