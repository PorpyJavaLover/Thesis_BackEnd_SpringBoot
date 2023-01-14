package com.thesis.scheduling.businesslevel.logic;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.NotTeach;
import com.thesis.scheduling.modellevel.mapper.NotTeachMapper;
import com.thesis.scheduling.modellevel.model.M_NotTeach_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_NotTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.NotTeachService;

@Service
public class NotTeachLogic {

	private final NotTeachService notteachservice;
	private final MemberService memberservice;
	private final NotTeachMapper notTeachMapper;

	public NotTeachLogic(NotTeachService notteachservice, MemberService memberservice, NotTeachMapper notTeachMapper) {
		this.notteachservice = notteachservice;
		this.memberservice = memberservice;
		this.notTeachMapper = notTeachMapper;

	}

	private int getCurrentUserId() {
		Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}

	// GET
	public Iterable<M_NotTeach_ShowAllTeacher_Response> showAllTeacher() {
		return notTeachMapper.toMShowAllTeacher(
				notteachservice.showAllByMemberId(memberservice.findByMemberId(getCurrentUserId()).get()));
	}

	public Iterable<NotTeach> showAllStaff() {
		return notteachservice.showAll();
	}

	// SET
	public void create(M_NotTeach_CreateTeacher_Request request) {
		notteachservice.create(memberservice.findByMemberId(getCurrentUserId()).get(),
				request.getDayOfWeek(), request.getTimeStart(), request.getTimeEnd());
	}
	
	public void update(int notId , M_NotTeach_CreateTeacher_Request request) {
		notteachservice.update(notId, request.getDayOfWeek(), request.getTimeStart(), request.getTimeEnd());
	}

	// DELETE
	public void delete(int notId) {
		notteachservice.delete(notId);
	}

}
