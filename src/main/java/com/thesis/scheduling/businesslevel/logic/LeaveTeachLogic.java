package com.thesis.scheduling.businesslevel.logic;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.mapper.LeaveTeachMapper;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_CreateStaff_Request;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.service.LeaveTeachService;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.ReplaceTeachService;
import com.thesis.scheduling.modellevel.service.TimetableService;

@Service
public class LeaveTeachLogic {

	private final LeaveTeachService leaveTeachService;
	private final MemberService memberService;
	private final LeaveTeachMapper leaveTeachMapper;
	private final ReplaceTeachService replaceTeachService;
	private final TimetableService timetableService;

	public LeaveTeachLogic(LeaveTeachService leaveTeachService, MemberService memberService,
			LeaveTeachMapper leaveTeachMapper, ReplaceTeachService replaceTeachService,
			TimetableService timetableService) {
		this.leaveTeachService = leaveTeachService;
		this.memberService = memberService;
		this.leaveTeachMapper = leaveTeachMapper;
		this.replaceTeachService = replaceTeachService;
		this.timetableService = timetableService;
	}

	private int getCurrentUserId() {
		Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}

	// GET
	public Iterable<M_LeaveTeach_ShowAllTeacher_Response> showAllTeacher(String year, String semester) {
		return leaveTeachMapper.toMShowAllTeacher(
			leaveTeachService.findAllByYearsAndSemesterAndMemberId(year, semester, memberService.findByMemberId(getCurrentUserId()).get()));
	}

	public Iterable<M_LeaveTeach_ShowAllTeacher_Response> showAllStaff(String year, String semester, int memberId) {
				return leaveTeachMapper.toMShowAllTeacher(
				leaveTeachService.findAllByYearsAndSemesterAndMemberId(year, semester, memberService.findByMemberId(memberId).get()));
	}

	// SET
	public void createTeacher(M_LeaveTeach_CreateTeacher_Request request) {
		//สร้างรายการงดสอน
		LeaveTeach sourceLeaveTeach = leaveTeachService.create(memberService.findByMemberId(getCurrentUserId()).get(),
				(String.valueOf(Integer.parseInt(request.getYear().toString()))),
				request.getSemester(), request.getDateStart(), request.getDateEnd(), request.getNote());
		Date dateStart = request.getDateStart(); 
		Date dateEnd = request.getDateEnd();
		Date dateStartTmp = new Date(dateStart.getYear(), dateStart.getMonth(), dateStart.getDate());
		Date dateEndTmp = new Date(dateEnd.getYear(), dateEnd.getMonth(), dateEnd.getDate());
		//หาคาบเรียน
		while (dateStartTmp.before(dateEndTmp) || dateStartTmp.equals(dateEndTmp)) {
			Calendar sourceA = Calendar.getInstance();
			sourceA.setTime(dateStartTmp);
			int dayOfWeek = sourceA.get(Calendar.DAY_OF_WEEK); 
			int dayNumber = dayOfWeek - 1;
			if (dayNumber == 0) {
				dayNumber = 7;
			}
			dateStartTmp.setDate(dateStartTmp.getDate() + 1);
			//หาวันที่ วัน เดือน ปี
			Date dateRun = new Date(dateStartTmp.getYear(), dateStartTmp.getMonth(), dateStartTmp.getDate());
			//หาตาราสอน 
			Collection<Timetable> sourceTimetable = timetableService 
					.findAllByAndMemberIdAndYearsAndSemesterAndDayOfWeek(
							memberService.findByMemberId(getCurrentUserId()).get(),
							(String.valueOf(Integer.parseInt(request.getYear().toString()))),
							request.getSemester(), dayOfWeek);

			if (sourceTimetable != null) {
				for (Timetable subTimetable : sourceTimetable) {
					replaceTeachService.create(sourceLeaveTeach, subTimetable, dateRun); //<-- สร้างรายการสอนแทน
				}
			}
		}
	}

	public void createStaff(M_LeaveTeach_CreateStaff_Request request) {

		System.out.println((String.valueOf(Integer.parseInt(request.getYear().toString()))) + "XXX"
		+ request.getDateStart() + "XXX" + request.getDateEnd());

		LeaveTeach sourceLeaveTeach = leaveTeachService.create(memberService.findByMemberId(request.getMemberId()).get(),
				(String.valueOf(Integer.parseInt(request.getYear().toString()))),
				request.getSemester(), request.getDateStart(), request.getDateEnd(), request.getNote());

		Date dateStart = request.getDateStart();
		Date dateEnd = request.getDateEnd();
		Date dateStartTmp = new Date(dateStart.getYear(), dateStart.getMonth(), dateStart.getDate());
		Date dateEndTmp = new Date(dateEnd.getYear(), dateEnd.getMonth(), dateEnd.getDate());

		System.out.println(dateStartTmp + ":::" + request.getDateStart());

		// แก้ไขเรื่องวันสิ้นสุดว่าให้สิ้นสุดด้วยเงื่อนไข "ลาถึงวันที่" หรือ
		// "วันที่กลับมาสอน"
		while (dateStartTmp.before(dateEndTmp) || dateStartTmp.equals(dateEndTmp)) {
			Calendar sourceA = Calendar.getInstance();
			sourceA.setTime(dateStartTmp);
			int dayOfWeek = sourceA.get(Calendar.DAY_OF_WEEK);
			int dayNumber = dayOfWeek - 1;
			if (dayNumber == 0) {
				dayNumber = 7;
			}

			dateStartTmp.setDate(dateStartTmp.getDate() + 1);
			Date dateRun = new Date(dateStartTmp.getYear(), dateStartTmp.getMonth(), dateStartTmp.getDate());

			System.out.println((String.valueOf(Integer.parseInt(request.getYear().toString()))) + ":::"
					+ dateStartTmp.getDate() + ":::" + dateRun);
					
			Collection<Timetable> sourceTimetable =  timetableService
					.findAllByAndMemberIdAndYearsAndSemesterAndDayOfWeek(
							memberService.findByMemberId(request.getMemberId()).get(),
							(String.valueOf(Integer.parseInt(request.getYear().toString()))),
							request.getSemester(), dayOfWeek);

			if (sourceTimetable != null) {
				for (Timetable subTimetable : sourceTimetable) {
					replaceTeachService.create(sourceLeaveTeach, subTimetable, dateRun);
				}
			}
		}
	}

	public void update(int leaveTeachId, M_LeaveTeach_CreateTeacher_Request request) {
		leaveTeachService.update(leaveTeachId, request.getYear(), request.getSemester(), request.getDateStart(),
				request.getDateEnd(), request.getNote());
	}

	// DELETE
	public void delete(int leaveTeachId) {
		replaceTeachService.deleteByLeaveTeachId(leaveTeachService.findByLeaveTeachId(leaveTeachId));
		leaveTeachService.delete(leaveTeachId);
	}
}
