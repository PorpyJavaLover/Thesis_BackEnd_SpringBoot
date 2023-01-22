package com.thesis.scheduling.businesslevel.logic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.NotTeach;
import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.mapper.TimetableMapper;
import com.thesis.scheduling.modellevel.model.M_Timetable_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_CreateStaff_Request;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowTimeRemain_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_UpdateLockerStaff_Request;
import com.thesis.scheduling.modellevel.model.M_Timetable_UpdateStaff_Request;
import com.thesis.scheduling.modellevel.service.RoomService;
import com.thesis.scheduling.modellevel.service.CourseService;
import com.thesis.scheduling.modellevel.service.GroupService;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.NotTeachService;
import com.thesis.scheduling.modellevel.service.TimetableService;

@Service
public class TimetableLogic {

	private final TimetableMapper mapper;

	private final TimetableService timetableService;
	private final MemberService memberService;
	private final CourseService courseService;
	private final GroupService groupService;
	private final NotTeachService notTeachService;
	private final RoomService roomService;

	public TimetableLogic(TimetableService timetableservice, MemberService memberService,
			CourseService courseservice, GroupService groupservice, TimetableMapper timetablemapper,
			RoomService roomService, NotTeachService notTeachService) {
		this.timetableService = timetableservice;
		this.memberService = memberService;
		this.courseService = courseservice;
		this.groupService = groupservice;
		this.mapper = timetablemapper;
		this.notTeachService = notTeachService;
		this.roomService = roomService;
	}

	private int getCurrentUserId() {
		Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}

	// GET
	public Iterable<M_Timetable_ShowAllTeacher_Response> showAllTeacherForPlan() {
		return mapper.toMTimetableTeacher(
				timetableService.findAllByMemberId(memberService.findByMemberId(getCurrentUserId()).get()));
	}

	public Iterable<M_Timetable_ShowAllStaff_Response> showAllStaff() {
		return mapper.toMTimetableStaff(timetableService.findAll());
	}

	public Iterable<M_Timetable_ShowAllTeacher_Response> showAllStaffByMemberIdForPlan(Integer memberId) {
		return mapper
				.toMTimetableTeacher(timetableService.findAllByMemberId(memberService.findByMemberId(memberId).get()));
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> showStartTimeOptionStaff(String yId, String sId, Long cId,
			Integer cType, Long gId,
			Integer dayOfWeek) {

		Iterable<Timetable> sourceA = timetableService
				.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupIdAndDayOfWeek(yId, sId,
						courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get(),
						dayOfWeek);

		Iterable<Member> sourceB = timetableService.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupId(yId,
				sId, courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());

		Collection<NotTeach> sourceC = new ArrayList<NotTeach>();

		Timetable sourceD = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(yId, sId,
				courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());

		for (Member sourceBTmp : sourceB) {
			sourceC.addAll(notTeachService.findAllByMemberIdAndDayOfWeek(sourceBTmp, dayOfWeek));
		}

		return mapper.toMTimeStartOptionStaff(sourceA, sourceC, sourceD);
	}

	public Iterable<M_Timetable_ShowTimeRemain_Response> showEndTimeOptionStaff(String yId, String sId, Long cId,
			Integer cType, Long gId,
			Integer dayOfWeek) {

		Iterable<Timetable> sourceA = timetableService
				.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupIdAndDayOfWeek(yId, sId,
						courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get(),
						dayOfWeek);

		Iterable<Member> sourceB = timetableService.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupId(yId,
				sId, courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());

		Collection<NotTeach> sourceC = new ArrayList<NotTeach>();

		Timetable sourceD = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(yId, sId,
				courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());
		for (Member sourceBTmp : sourceB) {
			sourceC.addAll(notTeachService.findAllByMemberIdAndDayOfWeek(sourceBTmp, dayOfWeek));
		}

		return mapper.toMTimeEndOptionStaff(sourceA, sourceC, sourceD);

	}

	public M_Timetable_ShowTimeRemain_Response showStartTimeStaff(String yId, String sId, Long cId, Integer cType,
			Long gId,
			Integer dayOfWeek, String endTime) {

		Timetable sourceA = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(yId, sId,
				courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());

		return mapper.toMTimeStartStaff(sourceA, endTime);

	}

	public M_Timetable_ShowTimeRemain_Response showEndTimeStaff(String yId, String sId, Long cId, Integer cType,
			Long gId,
			Integer dayOfWeek, String startTime) {

		Timetable sourceA = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(yId, sId,
				courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());

		return mapper.toMTimeEndStaff(sourceA, startTime);

	}

	public Iterable<M_For_Selection_Response> showRoomStaff(String yId, String sId, Long cId, Integer cType, Long gId,
			Integer dayOfWeek, Time startTime, Time endTime) {

		Iterable<Room> roomA = roomService.findAll();

		return mapper.toMRoomStaff(
				timetableService.findAllCollectionRoomByDayOfWeek(yId, sId, courseService.findByCourseId(cId).get(),
						cType,
						groupService.findByGroupId(gId).get(), dayOfWeek, startTime, endTime),
				roomA);
	}

	public void autoPilot() {

		Collection<Timetable> sourceA = timetableService.findAll();

		ArrayList<Timetable> list = new ArrayList<>(sourceA);

		Collections.sort(list, Comparator.comparing(t -> t.getGroupId().getGroupId()));

		Collections.reverse(list);

		for (Timetable listTmp : list) {
			if (listTmp.getDayOfWeek() == null || listTmp.getStartTime() == null || listTmp.getEndTime() == null) {
				Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

				targetA = showStartTimeOptionStaff(listTmp.getYears(),
						listTmp.getSemester(), listTmp.getCourseId().getCourseId(), listTmp.getCourseType(),
						listTmp.getGroupId().getGroupId(), 1);

				updateAutoPilotStaff(listTmp.getYears(),listTmp.getSemester(), 
				listTmp.getCourseId().getCourseId(), listTmp.getCourseType(),
				listTmp.getGroupId().getGroupId(), 1 ,targetA.iterator().next().getValue() , ,null );

			}
		}

	}

	// SET
	public void createTeacher(M_Timetable_CreateTeacher_Request request) {
		timetableService.create(request.getYears(), request.getSemester(),
				courseService.findByCourseId(request.getCourseId()).get(),
				request.getCourseType(),
				groupService.findByGroupId(request.getGroupId()).get(),
				memberService.findByMemberId(getCurrentUserId()).get());
	}

	public void createStaff(M_Timetable_CreateStaff_Request request) {
		timetableService.create(request.getYears(), request.getSemester(),
				courseService.findByCourseId(request.getCourseId()).get(),
				request.getCourseType(),
				groupService.findByGroupId(request.getGroupId()).get(),
				memberService.findByMemberId(request.getMemberId()).get());
	}

	public void updateStaff(String yId, String sId, Long cId, Integer cType, Long gId,
			M_Timetable_UpdateStaff_Request request) {

		String year = yId;
		String semeter = sId;
		Course courseId = courseService.findByCourseId(cId).get();
		Group groupId = groupService.findByGroupId(gId).get();
		Integer dayOfWeek = request.getDay_of_week() == null ? null : request.getDay_of_week();
		Time startTime = request.getStart_time() == null ? null : request.getStart_time();
		Time endTime = request.getEnd_time() == null ? null : request.getEnd_time();
		Room roomId = roomService.findAllByRoomId(request.getRoom_id()).isPresent() == false ? null
				: roomService.findAllByRoomId(request.getRoom_id()).get();

		timetableService.updateStaff(year, semeter, courseId, cType, groupId, dayOfWeek, startTime, endTime, roomId);

	}

	public void updateAutoPilotStaff(String yId, String sId, Long cId, Integer cType, Long gId,
	Integer day, Time start, Time end , Integer room) {

		String year = yId;
		String semeter = sId;
		Course courseId = courseService.findByCourseId(cId).get();
		Group groupId = groupService.findByGroupId(gId).get();
		Integer dayOfWeek = day;
		Time startTime = start;
		Time endTime = end;
		Room roomId = roomService.findAllByRoomId(room).isPresent() == false ? null
				: roomService.findAllByRoomId(room).get();

		timetableService.updateStaff(year, semeter, courseId, cType, groupId, dayOfWeek, startTime, endTime, roomId);
		
	}

	public void updateLockerStaff(String yId, String sId, Long cId, Integer cType, Long gId,
			M_Timetable_UpdateLockerStaff_Request request) {

		String year = yId;
		String semeter = sId;
		Course courseId = courseService.findByCourseId(cId).get();
		Group groupId = groupService.findByGroupId(gId).get();
		boolean timeLocker = request.isTime_locker();
		boolean roomLocker = request.isRoom_locker();
		timetableService.updateLockerStaff(year, semeter, courseId, cType, groupId, timeLocker, roomLocker);

	}

	// DELETE
	public void delete(int timetableId) {
		timetableService.delete(timetableId);
	}

	public void deleteForPlanTeacher(String yId, String sId, Long cId, Integer courseType, Long gId) {
		timetableService.deleteForPlan(yId, sId, courseService.findByCourseId(cId).get(), courseType,
				groupService.findByGroupId(gId).get(), memberService.findByMemberId(getCurrentUserId()).get());
	}

	public void deleteForPlanStaff(String yId, String sId, Long cId, Integer courseType, Long gId, Integer memberId) {
		timetableService.deleteForPlan(yId, sId, courseService.findByCourseId(cId).get(), courseType,
				groupService.findByGroupId(gId).get(), memberService.findByMemberId(memberId).get());
	}

}
