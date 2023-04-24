package com.thesis.scheduling.businesslevel.logic;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.mapper.OrganizationMapper;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.service.OrganizationService;

@Service
public class OrganizationLogic {

	private final OrganizationService organizationService;

	private final OrganizationMapper organizationMapper;

	public OrganizationLogic(OrganizationService organizationService, OrganizationMapper organizationMapper) {
		this.organizationService = organizationService;
		this.organizationMapper = organizationMapper;
	}

	public Iterable<M_For_Selection_Response> showAllTeacher() {
		return organizationMapper.toMShowTeacherForSelection(organizationService.showAll());
	}
	
	public Iterable<M_For_Selection_Response> showOrganizOption() {
		return organizationMapper.toMShowTeacherForSelection(organizationService.showOrganizationForSelection());
	}
	
	

}
