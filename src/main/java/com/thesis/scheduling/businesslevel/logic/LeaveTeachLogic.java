package com.thesis.scheduling.businesslevel.logic;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.mapper.LeaveTeachMapper;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_NotTeach_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.service.LeaveTeachService;
import com.thesis.scheduling.modellevel.service.MemberService;

@Service
public class LeaveTeachLogic {

	private final LeaveTeachService leaveTeachService;
	private final MemberService memberService;
	private final LeaveTeachMapper leaveTeachMapper;

	public LeaveTeachLogic(LeaveTeachService leaveTeachService, MemberService memberService,
			LeaveTeachMapper leaveTeachMapper) {
		this.leaveTeachService = leaveTeachService;
		this.memberService = memberService;
		this.leaveTeachMapper = leaveTeachMapper;
	}

	private int getCurrentUserId() {
		Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}

	// GET
	public Iterable<M_LeaveTeach_ShowAllTeacher_Response> showAllTeacher() {
		return leaveTeachMapper.toMShowAllTeacher(
				leaveTeachService.showAllByMemberId(memberService.findByMemberId(getCurrentUserId()).get()));
	}

	public Iterable<LeaveTeach> showAllStaff() {
		return leaveTeachService.showAll();
	}

	// SET
	public void createTeacher(M_LeaveTeach_CreateTeacher_Request request) {
		leaveTeachService.create(memberService.findByMemberId(getCurrentUserId()).get(), request.getYear(),
				request.getSemester(), request.getDateStart(), request.getDateEnd(), request.getNote());
	}
	
	public void update(int leaveTeachId , M_LeaveTeach_CreateTeacher_Request request) {
		leaveTeachService.update(leaveTeachId, request.getYear(), request.getSemester(), request.getDateStart(),request.getDateEnd(),request.getNote());
	}


	// DELETE
	public void deleteTeacher(int leaveTeachId) {
		leaveTeachService.delete(leaveTeachId);
	}
}
