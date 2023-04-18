package com.thesis.scheduling.businesslevel.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.mapper.ReplaceTeachMapper;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_UpdateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.LeaveTeachService;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.ReplaceTeachService;
import com.thesis.scheduling.modellevel.service.TimetableService;

@Service
public class ReplaceTeachLogic {

	private final MemberService memberService;
	private final ReplaceTeachService replaceTeachService;
	private final ReplaceTeachMapper replaceTeachMapper;
	private final LeaveTeachService leaveTeachService;
	private final TimetableService timetableService;

	public ReplaceTeachLogic(MemberService memberService, ReplaceTeachService replaceTeachService,
			ReplaceTeachMapper replaceTeachMapper, LeaveTeachService leaveTeachService,
			TimetableService timetableService) {
		this.memberService = memberService;
		this.replaceTeachService = replaceTeachService;
		this.replaceTeachMapper = replaceTeachMapper;
		this.leaveTeachService = leaveTeachService;
		this.timetableService = timetableService;
	}

	// GET
	public Iterable<M_ReplaceTeach_ShowAllTeacher_Response> showAllTeacher() {

		Collection<Timetable> sourceA = new ArrayList<Timetable>(timetableService.findAllByMemberId(memberService.findByMemberId(getCurrentUserId()).get()));
		Collection<ReplaceTeach> sourceB = new ArrayList<ReplaceTeach>();
		for (Timetable sourceATmp : sourceA) {
			sourceB.addAll(replaceTeachService.showAllByEssTimetableId(sourceATmp));
		}
		

		return replaceTeachMapper.toMShowAllTeacher(sourceB);
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
