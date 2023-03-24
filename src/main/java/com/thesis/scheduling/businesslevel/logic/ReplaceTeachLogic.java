package com.thesis.scheduling.businesslevel.logic;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.mapper.ReplaceTeachMapper;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_UpdateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.LeaveTeachService;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.ReplaceTeachService;

@Service
public class ReplaceTeachLogic {

	private final MemberService memberService;
	private final ReplaceTeachService replaceTeachService;
	private final ReplaceTeachMapper replaceTeachMapper;
	private final LeaveTeachService leaveTeachService;

	public ReplaceTeachLogic(MemberService memberService, ReplaceTeachService replaceTeachService,
			ReplaceTeachMapper replaceTeachMapper, LeaveTeachService leaveTeachService) {
		this.memberService = memberService;
		this.replaceTeachService = replaceTeachService;
		this.replaceTeachMapper = replaceTeachMapper;
		this.leaveTeachService = leaveTeachService;
	}
	

	// GET
	public Iterable<M_ReplaceTeach_ShowAllTeacher_Response> showAllTeacher() {
		return replaceTeachMapper.toMShowAllTeacher(
				replaceTeachService.showAllByMemberId(memberService.findByMemberId(getCurrentUserId()).get()));
	}

	public Iterable<ReplaceTeach> showAllStaff() {
		return replaceTeachService.showAll();
	}

	// SET
	public void updateTeacher(M_ReplaceTeach_UpdateTeacher_Request request) {
		replaceTeachService.updateTeacher(request.getReplaceTeachId() ,memberService.findByMemberId(request.getMemberId()).get());
	}

	// DELETE
	public void deleteTeacher(int replaceTeachId) {
		replaceTeachService.delete(replaceTeachId);
	}

	private int getCurrentUserId() {
		final Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}
}
