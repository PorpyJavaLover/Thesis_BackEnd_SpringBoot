package com.thesis.scheduling.businesslevel.logic;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.mapper.ReplaceTeachMapper;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.ReplaceTeachService;

@Service
public class ReplaceTeachLogic {

	private final MemberService memberService;
	private final ReplaceTeachService replaceTeachService;
	private final ReplaceTeachMapper replaceTeachMapper;

	public ReplaceTeachLogic(ReplaceTeachService replaceteachservice, MemberService memberService,
			ReplaceTeachMapper replaceTeachMapper) {
		this.memberService = memberService;
		this.replaceTeachService = replaceteachservice;
		this.replaceTeachMapper = replaceTeachMapper;
	}

	private int getCurrentUserId() {
		Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
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
	public void createTeacher(M_ReplaceTeach_CreateTeacher_Request request) {
		replaceTeachService.create(request.getLeaveTeachId(), request.getEssTimetableId(),
				memberService.findByMemberId(getCurrentUserId()).get());
	}

	// DELETE
	public void deleteTeacher(int replaceTeachId) {
		replaceTeachService.delete(replaceTeachId);
	}
}
