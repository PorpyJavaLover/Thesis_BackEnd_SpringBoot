package com.thesis.scheduling.businesslevel.logic;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Organization;
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
		return organizationMapper.toMShowForSelection(organizationService.showAll());
	}

	public Iterable<M_For_Selection_Response> showFacultyOption() {
		return  organizationMapper.toMShowForSelection(organizationService.findAllByParentAndType("1000", 30));
	}
	
	public Iterable<M_For_Selection_Response> showOrganizOption(String parent) {

		Collection<Organization> source = organizationService.findAllByParentAndType(parent, 41);

		Collection<Organization> targetSub = new ArrayList<Organization>();

		for (Organization sourceTmp : source) {
			targetSub.addAll(organizationService.findAllByParentAndType(sourceTmp.getCode(), 52));
		}

		return organizationMapper.toMShowForSelection(targetSub);
	}
	
	

}
