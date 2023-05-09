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
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_PDFBodyTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_PDFHeadTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.LeaveTeachService;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.OrganizationService;
import com.thesis.scheduling.modellevel.service.ReplaceTeachService;
import com.thesis.scheduling.modellevel.service.TimetableService;

@Service
public class ReplaceTeachLogic {

	private final MemberService memberService;
	private final ReplaceTeachService replaceTeachService;
	private final ReplaceTeachMapper replaceTeachMapper;
	private final LeaveTeachService leaveTeachService;
	private final TimetableService timetableService;
	private final OrganizationService organizationService;

	public ReplaceTeachLogic(MemberService memberService, ReplaceTeachService replaceTeachService,
			ReplaceTeachMapper replaceTeachMapper, LeaveTeachService leaveTeachService,
			TimetableService timetableService, OrganizationService organizationService) {
		this.memberService = memberService;
		this.replaceTeachService = replaceTeachService;
		this.replaceTeachMapper = replaceTeachMapper;
		this.leaveTeachService = leaveTeachService;
		this.timetableService = timetableService;
		this.organizationService = organizationService;
	}

	// GET
	public Iterable<M_ReplaceTeach_ShowAllTeacher_Response> showAllTeacher(String year, String semester) {

		Collection<Timetable> sourceA = new ArrayList<Timetable>(
				timetableService.findAllByYearsAndSemesterAndMemberId(year, semester,
						memberService.findByMemberId(getCurrentUserId()).get()));
		Collection<ReplaceTeach> sourceB = new ArrayList<ReplaceTeach>();
		for (Timetable sourceATmp : sourceA) {
			sourceB.addAll(replaceTeachService.findAllByEssTimetableId(sourceATmp));
		}
		return replaceTeachMapper.toMShowAllTeacher(sourceB);
	}

	public Iterable<M_ReplaceTeach_ShowAllTeacher_Response> showAllStaff(String year, String semester, int memberId) {

		Collection<Timetable> sourceA = new ArrayList<Timetable>(
				timetableService.findAllByYearsAndSemesterAndMemberId(year, semester,
						memberService.findByMemberId(memberId).get()));
		Collection<ReplaceTeach> sourceB = new ArrayList<ReplaceTeach>();
		for (Timetable sourceATmp : sourceA) {
			sourceB.addAll(replaceTeachService.findAllByEssTimetableId(sourceATmp));
		}
		return replaceTeachMapper.toMShowAllTeacher(sourceB);
	}

	public Iterable<M_SelectOption_Response> showMemberReplaceOption(int replaceTeachId, String sOrganize) {

		Collection<Member> sourceA = new ArrayList<Member>(
				memberService.findAllBySOrganizationId(organizationService.findByCode(sOrganize).get()));
		sourceA.remove(memberService.findByMemberId(getCurrentUserId()).get());
		Collection<Member> sourceD = new ArrayList<Member>(sourceA);
		for (Member sourceDTmp : sourceD) {
			ReplaceTeach sourceC = replaceTeachService.findByReplaceTeachId(replaceTeachId).get();
			Collection<Timetable> sourceB = timetableService.findAllByAndMemberIdAndYearsAndSemesterAndDayOfWeek(
					sourceDTmp, sourceC.getEssTimetableId().getYears(), sourceC.getEssTimetableId().getSemester(),
					sourceC.getEssTimetableId().getDayOfWeek());
			Integer j = 0;
			if (sourceC.getEssTimetableId().getCourseType() == 0) {
				j = sourceC.getEssTimetableId().getCourseId().getCourseLect();
			} else if (sourceC.getEssTimetableId().getCourseType() == 1) {
				j = (sourceC.getEssTimetableId().getCourseId().getCoursePerf());
			}
			sourceC.getEssTimetableId().getStartTime();
			if (sourceB != null) {
				loop1 : for (Timetable sourceBTmp : sourceB) {
					for (int i = 0; i < j; i++) {
						int A = deconvertStartTime(sourceBTmp.getStartTime().toString()) + i;
						int B = deconvertStartTime(sourceC.getEssTimetableId().getStartTime().toString());
						if (A == B) {
							sourceA.remove(sourceBTmp.getMemberId());
							break loop1;
						}
					}

				}
			}
		}

		return replaceTeachMapper.toMemberReplaceOptionTeacher(sourceA);
	}

	public M_ReplaceTeach_PDFHeadTeacher_Response showPDFHeadTeacher(int replaceTeachId) {
		Optional<ReplaceTeach> sourceC = replaceTeachService.findByReplaceTeachId(replaceTeachId);
		return replaceTeachMapper.toPDFHeadTeacher(sourceC.get());
	}

	public Iterable<M_ReplaceTeach_PDFBodyTeacher_Response> showPDFBodyTeacher(int leaveTeachId, int replaceTeachId) {
		Iterable<ReplaceTeach> sourceC = replaceTeachService.findAllByLeaveTeachIdAndMemberReplaceId(
				leaveTeachService.findByLeaveTeachId(leaveTeachId),
				replaceTeachService.findByReplaceTeachId(replaceTeachId).get().getMemberReplaceId());
		return replaceTeachMapper.toPDFBodyTeacher(sourceC);
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

	// function
	public int deconvertStartTime(String input) {
		int output = 0;
		switch (input) {
			case "08:00:00":
				output = 1;
				break;
			case "09:00:00":
				output = 2;
				break;
			case "10:00:00":
				output = 3;
				break;
			case "11:00:00":
				output = 4;
				break;
			case "12:00:00":
				output = 5;
				break;
			case "13:00:00":
				output = 6;
				break;
			case "14:00:00":
				output = 7;
				break;
			case "15:00:00":
				output = 8;
				break;
			case "16:00:00":
				output = 9;
				break;
			case "17:00:00":
				output = 10;
				break;
			case "18:00:00":
				output = 11;
				break;
			case "19:00:00":
				output = 12;
				break;
			case "20:00:00":
				output = 13;
				break;
			case "21:00:00":
				output = 14;
				break;
		}
		return output;
	}

	public String convertStartTime(int input) {
		String output = "08:00:00";
		switch (input) {
			case 1:
				output = "08:00:00";
				break;
			case 2:
				output = "09:00:00";
				break;
			case 3:
				output = "10:00:00";
				break;
			case 4:
				output = "11:00:00";
				break;
			case 5:
				output = "12:00:00";
				break;
			case 6:
				output = "13:00:00";
				break;
			case 7:
				output = "14:00:00";
				break;
			case 8:
				output = "15:00:00";
				break;
			case 9:
				output = "16:00:00";
				break;
			case 10:
				output = "17:00:00";
				break;
			case 11:
				output = "18:00:00";
				break;
			case 12:
				output = "19:00:00";
				break;
			case 13:
				output = "20:00:00";
				break;
			case 14:
				output = "21:00:00";
				break;
		}
		return output;
	}
}
