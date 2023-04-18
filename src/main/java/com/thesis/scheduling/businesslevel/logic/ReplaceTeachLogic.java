package com.thesis.scheduling.businesslevel.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.mapper.ReplaceTeachMapper;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_UpdateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_SelectOption_Response;
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

		Collection<Timetable> sourceA = new ArrayList<Timetable>(
				timetableService.findAllByMemberId(memberService.findByMemberId(getCurrentUserId()).get()));
		Collection<ReplaceTeach> sourceB = new ArrayList<ReplaceTeach>();
		for (Timetable sourceATmp : sourceA) {
			sourceB.addAll(replaceTeachService.showAllByEssTimetableId(sourceATmp));
		}
		return replaceTeachMapper.toMShowAllTeacher(sourceB);
	}

	public Iterable<M_SelectOption_Response> showMemberReplaceOption(int replaceTeachId) {

		Collection<Member> sourceA = new ArrayList<Member>(memberService.findAll());
		sourceA.remove(memberService.findByMemberId(getCurrentUserId()).get());
		for (Member sourceATmp : sourceA) {
			Collection<Timetable> sourceB = timetableService.findAllByMemberId(sourceATmp);
			ReplaceTeach sourceC = replaceTeachService.findByReplaceTeachId(replaceTeachId).get();
			Integer j = 0;
			if (sourceC.getEssTimetableId().getCourseType() == 0) {
				j = sourceC.getEssTimetableId().getCourseId().getCourseLect();
			} else if (sourceC.getEssTimetableId().getCourseType() == 1) {
				j = sourceC.getEssTimetableId().getCourseId().getCoursePerf();
			}
			sourceC.getEssTimetableId().getStartTime();
			for (Timetable sourceBTmp : sourceB) {
				boolean breakChecker = false;
				for (int i = 0; i < j ; i++) {
					int A = deconvertEndTime(sourceBTmp.getStartTime().toString()) + i;
					int B = deconvertEndTime(sourceC.getEssTimetableId().getStartTime().toString());
					if(A == B){
						breakChecker = true;
						sourceA.remove(sourceBTmp.getMemberId());
						break;
					}
				}
				if(breakChecker == true){
					break;
				}

			}
		}
		return replaceTeachMapper.toMemberReplaceOptionTeacher(sourceA);
	}

	public Iterable<ReplaceTeach> showAllStaff() {
		return replaceTeachService.showAll();
	}

	// SET
	public void updateTeacher(int replaceTeachId, M_ReplaceTeach_UpdateTeacher_Request request) {
		replaceTeachService.updateTeacher(replaceTeachId,
				memberService.findByMemberId(request.getMemberReplaceId()).get());
	}

	// DELETE
	public void deleteTeacher(int replaceTeachId) {
		replaceTeachService.delete(replaceTeachId);
	}

	private int getCurrentUserId() {
		final Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}

	//function
	public int deconvertEndTime(String input) {
		int output = 0;
		switch (input) {

			case "09:00:00":
				output = 1;
				break;
			case "10:00:00":
				output = 2;
				break;
			case "11:00:00":
				output = 3;
				break;
			case "12:00:00":
				output = 4;
				break;
			case "13:00:00":
				output = 5;
				break;
			case "14:00:00":
				output = 6;
				break;
			case "15:00:00":
				output = 7;
				break;
			case "16:00:00":
				output = 8;
				break;
			case "17:00:00":
				output = 9;
				break;
			case "18:00:00":
				output = 10;
				break;
			case "19:00:00":
				output = 11;
				break;
			case "20:00:00":
				output = 12;
				break;
			case "21:00:00":
				output = 13;
				break;
			case "22:00:00":
				output = 14;
				break;
		}
		return output;
	}

	public String convertEndTime(int input) {
		String output = "XX:XX:XX";
		switch (input) {
			case 1:
				output = "09:00:00";
				break;
			case 2:
				output = "10:00:00";
				break;
			case 3:
				output = "11:00:00";
				break;
			case 4:
				output = "12:00:00";
				break;
			case 5:
				output = "13:00:00";
				break;
			case 6:
				output = "14:00:00";
				break;
			case 7:
				output = "15:00:00";
				break;
			case 8:
				output = "16:00:00";
				break;
			case 9:
				output = "17:00:00";
				break;
			case 10:
				output = "18:00:00";
				break;
			case 11:
				output = "19:00:00";
				break;
			case 12:
				output = "20:00:00";
				break;
			case 13:
				output = "21:00:00";
				break;
			case 14:
				output = "22:00:00";
				break;
		}
		return output;
	}
}
