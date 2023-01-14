package com.thesis.scheduling.businesslevel.logic;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.mapper.OrganizationMapper;
import com.thesis.scheduling.modellevel.model.M_Organization_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.OrganizationService;

@Service
public class OrganizationLogic {

	private final OrganizationService organizationService;

	private final OrganizationMapper organizationMapper;

	public OrganizationLogic(OrganizationService organizationService, OrganizationMapper organizationMapper) {
		this.organizationService = organizationService;
		this.organizationMapper = organizationMapper;
	}

	public Iterable<M_Organization_ShowAllTeacher_Response> showAllTeacher() {
		return organizationMapper.toMShowAllTeacher(organizationService.showAll());
	}
	
	public Iterable<M_Organization_ShowAllTeacher_Response> showSelectTeacher() {
		return organizationMapper.toMShowAllTeacher(organizationService.showSelect());
	}
	
	

}
