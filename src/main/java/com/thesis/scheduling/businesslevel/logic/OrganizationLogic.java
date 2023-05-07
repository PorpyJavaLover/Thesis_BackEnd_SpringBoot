package com.thesis.scheduling.businesslevel.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.mapper.OrganizationMapper;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Organiz_Response;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.OrganizationService;

@Service
public class OrganizationLogic {

	private final OrganizationService organizationService;
	private final MemberService memberService;

	private final OrganizationMapper organizationMapper;

	public OrganizationLogic(OrganizationService organizationService, MemberService memberService,
			OrganizationMapper organizationMapper) {
		this.organizationService = organizationService;
		this.memberService = memberService;
		this.organizationMapper = organizationMapper;
	}

	private int getCurrentUserId() {
		Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}

	public Iterable<M_For_Selection_Response> showAllTeacher() {
		return organizationMapper.toMShowForSelection(organizationService.showAll());
	}

	public Iterable<M_For_Selection_Response> showFacultyOption() {
		return organizationMapper.toMShowForSelection(organizationService.findAllByParentAndType("1000", 30));
	}

	public Iterable<M_For_Selection_Response> showOrganizOption(String parent) {

		Collection<Organization> source = organizationService.findAllByParentAndType(parent, 41);

		Collection<Organization> targetSub = new ArrayList<Organization>();

		for (Organization sourceTmp : source) {
			targetSub.addAll(organizationService.findAllByParentAndType(sourceTmp.getCode(), 52));
		}

		return organizationMapper.toMShowForSelection(targetSub);
	}

	public M_Member_Organiz_Response showMemberTeacherOrganiz() {
		Member sourceA = memberService.findByMemberId(getCurrentUserId()).get();
		Organization sourceB = organizationService.findByCode(sourceA.getOrganizationId().getParent()).get();
		return organizationMapper.toMShowMemberOrganiz(sourceA, sourceB);
	}

	public M_Member_Organiz_Response showMemberStaffOrganiz(int memberId) {
		Member sourceA = memberService.findByMemberId(memberId).get();
		Organization sourceB = organizationService.findByCode(sourceA.getOrganizationId().getParent()).get();
		return organizationMapper.toMShowMemberOrganiz(sourceA, sourceB);
	}

}
