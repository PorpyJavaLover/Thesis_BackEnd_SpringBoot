package com.thesis.scheduling.businesslevel.logic;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.NotTeach;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.mapper.TimetableMapper;
import com.thesis.scheduling.modellevel.model.M_Timetable_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_CreateStaff_Request;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowTable_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowTimeRemain_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_UpdateLockerStaff_Request;
import com.thesis.scheduling.modellevel.model.M_Timetable_UpdateStaff_Request;
import com.thesis.scheduling.modellevel.service.RoomService;
import com.thesis.scheduling.modellevel.service.CourseService;
import com.thesis.scheduling.modellevel.service.GroupService;
import com.thesis.scheduling.modellevel.service.LeaveTeachService;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.NotTeachService;
import com.thesis.scheduling.modellevel.service.ReplaceTeachService;
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
	private final LeaveTeachService leaveTeachService;
	private final ReplaceTeachService replaceTeachService;

	public TimetableLogic(TimetableMapper mapper, TimetableService timetableService, MemberService memberService,
			CourseService courseService, GroupService groupService, NotTeachService notTeachService,
			RoomService roomService, LeaveTeachService leaveTeachService, ReplaceTeachService replaceTeachService) {
		this.mapper = mapper;
		this.timetableService = timetableService;
		this.memberService = memberService;
		this.courseService = courseService;
		this.groupService = groupService;
		this.notTeachService = notTeachService;
		this.roomService = roomService;
		this.leaveTeachService = leaveTeachService;
		this.replaceTeachService = replaceTeachService;
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

	public Iterable<M_Timetable_ShowAllStaff_Response> showAllTeacher(String yId, String sId) {
		Collection<Timetable> sourceA = new ArrayList<Timetable>(
				timetableService.findAllByYearsAndSemesterAndMemberId(yId, sId,
						memberService.findByMemberId(getCurrentUserId()).get()));
		Collection<Timetable> sourceForRemove = new ArrayList<Timetable>();
		Collection<M_Timetable_ShowAllStaff_Response> sourceD = new ArrayList<M_Timetable_ShowAllStaff_Response>();

		for (Timetable sourceATmp : sourceA) {
			Collection<Timetable> sourceCoopTeacher;
			String sourceYId = sourceATmp.getYears();
			String sourceSId = sourceATmp.getSemester();
			Course sourceCId = courseService.findByCourseId(sourceATmp.getCourseId().getCourseId()).get();
			Integer sourceCType = sourceATmp.getCourseType();
			Group sourceGId = groupService.findByGroupId(sourceATmp.getGroupId().getGroupId()).get();
			sourceCoopTeacher = timetableService.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(sourceYId,
					sourceSId, sourceCId, sourceCType, sourceGId);
			for (Timetable sourceCoopTeacherTmp : sourceCoopTeacher) {
				boolean SameTimetable = true;
				for (Timetable sourceForRemoveTmp : sourceForRemove) {
					if (sourceForRemoveTmp.equals(sourceATmp)) {
						SameTimetable = false;
						break;
					}
				}
				if (SameTimetable && !sourceCoopTeacherTmp.equals(sourceATmp)) {
					sourceForRemove.add(sourceCoopTeacherTmp);
				}
			}
		}

		sourceA.removeAll(sourceForRemove);

		for (Timetable sourceATmp : sourceA) {
			String sourceYId = sourceATmp.getYears();
			String sourceSId = sourceATmp.getSemester();
			Course sourceCId = courseService.findByCourseId(sourceATmp.getCourseId().getCourseId()).get();
			Integer sourceCType = sourceATmp.getCourseType();
			Group sourceGId = groupService.findByGroupId(sourceATmp.getGroupId().getGroupId()).get();
			Collection<Timetable> sourceC = timetableService
					.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(sourceYId, sourceSId, sourceCId,
							sourceCType, sourceGId);
			M_Timetable_ShowAllStaff_Response sourceDTmp = mapper.toMTimetableStaff(sourceATmp, sourceC);
			sourceD.add(sourceDTmp);
		}
		return sourceD;
	}

	public Iterable<M_Timetable_ShowAllStaff_Response> showAllStaff(String yId, String sId) {
		Collection<Timetable> sourceA = new ArrayList<Timetable>(timetableService.findAllByYearsAndSemester(yId, sId));
		Collection<Timetable> sourceForRemove = new ArrayList<Timetable>();
		Collection<M_Timetable_ShowAllStaff_Response> sourceD = new ArrayList<M_Timetable_ShowAllStaff_Response>();

		for (Timetable sourceATmp : sourceA) {
			Collection<Timetable> sourceCoopTeacher;
			String sourceYId = sourceATmp.getYears();
			String sourceSId = sourceATmp.getSemester();
			Course sourceCId = courseService.findByCourseId(sourceATmp.getCourseId().getCourseId()).get();
			Integer sourceCType = sourceATmp.getCourseType();
			Group sourceGId = groupService.findByGroupId(sourceATmp.getGroupId().getGroupId()).get();
			sourceCoopTeacher = timetableService.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(sourceYId,
					sourceSId, sourceCId, sourceCType, sourceGId);
			for (Timetable sourceCoopTeacherTmp : sourceCoopTeacher) {
				boolean SameTimetable = true;
				for (Timetable sourceForRemoveTmp : sourceForRemove) {
					if (sourceForRemoveTmp.equals(sourceATmp)) {
						SameTimetable = false;
						break;
					}
				}
				if (SameTimetable && !sourceCoopTeacherTmp.equals(sourceATmp)) {
					sourceForRemove.add(sourceCoopTeacherTmp);
				}
			}
		}

		sourceA.removeAll(sourceForRemove);

		for (Timetable sourceATmp : sourceA) {
			String sourceYId = sourceATmp.getYears();
			String sourceSId = sourceATmp.getSemester();
			Course sourceCId = courseService.findByCourseId(sourceATmp.getCourseId().getCourseId()).get();
			Integer sourceCType = sourceATmp.getCourseType();
			Group sourceGId = groupService.findByGroupId(sourceATmp.getGroupId().getGroupId()).get();
			Collection<Timetable> sourceC = timetableService
					.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(sourceYId, sourceSId, sourceCId,
							sourceCType, sourceGId);
			M_Timetable_ShowAllStaff_Response sourceDTmp = mapper.toMTimetableStaff(sourceATmp, sourceC);
			sourceD.add(sourceDTmp);
		}
		return sourceD;
	}

	public Iterable<M_Timetable_ShowAllTeacher_Response> showAllStaffByMemberIdForPlan(Integer memberId) {
		return mapper
				.toMTimetableTeacher(timetableService.findAllByMemberId(memberService.findByMemberId(memberId).get()));
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> showStartTimeOptionStaff(boolean modeForAutoPilot,
			String yId,
			String sId, Long cId,
			Integer cType, Long gId, Integer dayOfWeek) {

		Iterable<Timetable> sourceCoTeach; // อาจารย์ที่สอนร่วมกัน
		Iterable<Member> sourceEachTeacher; // อาจารย์แต่ละคน
		Collection<NotTeach> sourceInconvenientTeach; // หาวันที่ไม่สะดวกสอน
		Integer sourceCourseType; // ประเภทวิชา
		Collection<Timetable> sourceEachClassToDay; // คาบเรียนของกลุ่มเรียนในวันนี้
		Collection<Timetable> sourceSelfTimetable; // รายการตัวเอง

		Collection<M_Timetable_ShowTimeRemain_Response> MBase;
		Collection<M_Timetable_ShowTimeRemain_Response> MCoTeach;
		Collection<M_Timetable_ShowTimeRemain_Response> MInconvenientTeach;
		Collection<M_Timetable_ShowTimeRemain_Response> MEachClassToDay;
		Collection<M_Timetable_ShowTimeRemain_Response> MUnion;
		List<M_Timetable_ShowTimeRemain_Response> MUnionSort;
		Collection<M_Timetable_ShowTimeRemain_Response> MUnionSorted;

		sourceCoTeach = timetableService.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupIdAndDayOfWeek(yId,
				sId, courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get(), dayOfWeek);
		sourceEachTeacher = timetableService.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupId(yId,
				sId, courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());
		sourceInconvenientTeach = new ArrayList<NotTeach>();
		for (Member sourceBTmp : sourceEachTeacher) {
			sourceInconvenientTeach.addAll(notTeachService.findAllByMemberIdAndDayOfWeek(sourceBTmp, dayOfWeek));
		}
		sourceCourseType = timetableService.getCourseType(yId, sId, courseService.findByCourseId(cId).get(), cType,
				groupService.findByGroupId(gId).get());
		sourceEachClassToDay = timetableService.findAllCollectionMemberBGroupIdAndDayOfWeek(yId, sId,
				groupService.findByGroupId(gId).get(), dayOfWeek);
		sourceSelfTimetable = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndDayOfWeek(yId,
				sId, courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get(), dayOfWeek);

		if (sourceSelfTimetable != null) {
			sourceEachClassToDay.removeAll(sourceSelfTimetable);
		}

		MBase = mapper.getMTimeStartBase();
		MCoTeach = mapper.toMTimeStartCoTeach(sourceCoTeach);
		MInconvenientTeach = mapper.toMTimeStartInconvenient(sourceInconvenientTeach);
		MEachClassToDay = mapper.toMTimeStartEachClassToDay(sourceEachClassToDay);

		if (modeForAutoPilot) {
			MUnion = mapper.UnionDayAndTimeForAuto(MBase, MCoTeach, MInconvenientTeach, MEachClassToDay);
		} else {
			MUnion = mapper.UnionDayAndTime(MBase, MCoTeach, MInconvenientTeach, MEachClassToDay);
		}

		MUnionSort = ((ArrayList<M_Timetable_ShowTimeRemain_Response>) MUnion).subList(0,
				MUnion.size() - (sourceCourseType) + 1);
		MUnionSorted = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		MUnionSorted.addAll(MUnion);
		MUnion.removeAll(MUnionSort);
		MUnionSorted.removeAll(MUnion);

		return MUnionSorted;
	}

	public Iterable<M_Timetable_ShowTimeRemain_Response> showEndTimeOptionStaff(String yId, String sId, Long cId,
			Integer cType, Long gId, Integer dayOfWeek) {

		Iterable<Timetable> sourceCoTeach; // อาจารย์ที่สอนร่วมกัน
		Iterable<Member> sourceEachTeacher; // อาจารย์แต่ละคน
		Collection<NotTeach> sourceInconvenientTeach; // หาวันที่ไม่สะดวกสอน
		Integer sourceCourseType; // ประเภทวิชา
		Collection<Timetable> sourceEachClassToDay; // คาบเรียนของกลุ่มเรียนในวันนี้
		Collection<Timetable> sourceSelfTimetable; // รายการตัวเอง

		Collection<M_Timetable_ShowTimeRemain_Response> MBase;
		Collection<M_Timetable_ShowTimeRemain_Response> MCoTeach;
		Collection<M_Timetable_ShowTimeRemain_Response> MInconvenientTeach;
		Collection<M_Timetable_ShowTimeRemain_Response> MEachClassToDay;
		Collection<M_Timetable_ShowTimeRemain_Response> MUnion;
		List<M_Timetable_ShowTimeRemain_Response> MUnionSort;
		Collection<M_Timetable_ShowTimeRemain_Response> MUnionSorted;

		sourceCoTeach = timetableService.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupIdAndDayOfWeek(yId,
				sId,
				courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get(), dayOfWeek);
		sourceEachTeacher = timetableService.findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupId(yId,
				sId, courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());
		sourceInconvenientTeach = new ArrayList<NotTeach>();
		for (Member sourceBTmp : sourceEachTeacher) {
			sourceInconvenientTeach.addAll(notTeachService.findAllByMemberIdAndDayOfWeek(sourceBTmp, dayOfWeek));
		}
		sourceCourseType = timetableService.getCourseType(yId, sId, courseService.findByCourseId(cId).get(), cType,
				groupService.findByGroupId(gId).get());
		sourceEachClassToDay = timetableService.findAllCollectionMemberBGroupIdAndDayOfWeek(yId, sId,
				groupService.findByGroupId(gId).get(), dayOfWeek);
		sourceSelfTimetable = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndDayOfWeek(yId,
				sId, courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get(), dayOfWeek);

		if (sourceSelfTimetable != null) {
			sourceEachClassToDay.removeAll(sourceSelfTimetable);
		}

		MBase = mapper.getMTimeEndBase();
		MCoTeach = mapper.toMTimeEndCoTeach(sourceCoTeach);
		MInconvenientTeach = mapper.toMTimeEndInconvenient(sourceInconvenientTeach);
		MEachClassToDay = mapper.toMTimeEndEachClassToDay(sourceEachClassToDay);

		MUnion = mapper.UnionDayAndTime(MBase, MCoTeach, MInconvenientTeach, MEachClassToDay);

		MUnionSort = ((ArrayList<M_Timetable_ShowTimeRemain_Response>) MUnion).subList(sourceCourseType - 1,
				MUnion.size());
		MUnionSorted = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		MUnionSorted.addAll(MUnion);
		MUnion.removeAll(MUnionSort);
		MUnionSorted.removeAll(MUnion);

		return MUnionSorted;
	}

	public M_Timetable_ShowTimeRemain_Response showStartTimeStaff(String yId, String sId, Long cId, Integer cType,
			Long gId, Integer dayOfWeek, String endTime) {

		Timetable sourceA = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(yId, sId,
				courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());

		return mapper.toMTimeStartStaff(sourceA, endTime);

	}

	public M_Timetable_ShowTimeRemain_Response showEndTimeStaff(String yId, String sId, Long cId, Integer cType,
			Long gId, Integer dayOfWeek, String startTime) {

		Timetable sourceA = timetableService.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(yId, sId,
				courseService.findByCourseId(cId).get(), cType, groupService.findByGroupId(gId).get());

		return mapper.toMTimeEndStaff(sourceA, startTime);

	}

	public Iterable<M_For_Selection_Response> showRoomStaff(boolean modeForAutoPilot, String yId, String sId, Long cId,
			Integer cType, Long gId,
			Integer dayOfWeek, Time startTime, Time endTime) {

		Iterable<Room> roomA = roomService.findAll();

		return mapper.toMRoomStaff(modeForAutoPilot,
				timetableService.findAllCollectionRoomByDayOfWeek(yId, sId, courseService.findByCourseId(cId).get(),
						cType, groupService.findByGroupId(gId).get(), dayOfWeek, startTime, endTime),
				roomA);

	}

	public void clean(String yId, String sId, Long cId, Integer cType, Long gId) {

		Collection<Timetable> sourceA = timetableService
				.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(yId,
						sId, courseService.findByCourseId(cId).get(), cType,
						groupService.findByGroupId(gId).get());

		for (Timetable sourceATmp : sourceA) {

			Collection<ReplaceTeach> sourceB = replaceTeachService.findAllByEssTimetableId(sourceATmp);

			for (ReplaceTeach sourceBTmp : sourceB) {
				replaceTeachService.deleteByLeaveTeachId(
						leaveTeachService.findByLeaveTeachId(sourceBTmp.getLeaveTeachId().getLeaveTeachId()));
				leaveTeachService.delete(sourceBTmp.getLeaveTeachId().getLeaveTeachId());
			}
		}

		for (Timetable sourceATmp : sourceA) {

			Integer sourceDay = null;
			Time sourceStartTime = null;
			Time sourceEndTime = null;
			Integer sourceRoom = null;

			if (sourceATmp.isTimeLocker()) {
				sourceDay = sourceATmp.getDayOfWeek() == null ? null : sourceATmp.getDayOfWeek();
				sourceStartTime = sourceATmp.getStartTime() == null ? null : sourceATmp.getStartTime();
				sourceEndTime = sourceATmp.getEndTime() == null ? null : sourceATmp.getEndTime();
			}
			if (sourceATmp.isRoomLocker()) {
				sourceRoom = sourceATmp.getRoomId() == null ? null : sourceATmp.getRoomId().getRoomId();
			}

			updateAutoPilotStaff(sourceATmp.getYears(), sourceATmp.getSemester(),
					sourceATmp.getCourseId().getCourseId(), sourceATmp.getCourseType(),
					sourceATmp.getGroupId().getGroupId(), sourceDay, sourceStartTime, sourceEndTime, sourceRoom);

		}
	}

	public void cleanAll(String yId, String sId) {

		Collection<Timetable> sourceA = timetableService.findAllByYearsAndSemester(yId, sId);

		for (Timetable sourceATmp : sourceA) {

			Collection<ReplaceTeach> sourceB = replaceTeachService.findAllByEssTimetableId(sourceATmp);

			for (ReplaceTeach sourceBTmp : sourceB) {
				replaceTeachService.deleteByLeaveTeachId(
						leaveTeachService.findByLeaveTeachId(sourceBTmp.getLeaveTeachId().getLeaveTeachId()));
				leaveTeachService.delete(sourceBTmp.getLeaveTeachId().getLeaveTeachId());
			}
		}

		for (Timetable sourceATmp : sourceA) {

			Integer sourceDay = null;
			Time sourceStartTime = null;
			Time sourceEndTime = null;
			Integer sourceRoom = null;

			if (sourceATmp.isTimeLocker()) {
				sourceDay = sourceATmp.getDayOfWeek() == null ? null : sourceATmp.getDayOfWeek();
				sourceStartTime = sourceATmp.getStartTime() == null ? null : sourceATmp.getStartTime();
				sourceEndTime = sourceATmp.getEndTime() == null ? null : sourceATmp.getEndTime();
			}
			if (sourceATmp.isRoomLocker()) {
				sourceRoom = sourceATmp.getRoomId() == null ? null : sourceATmp.getRoomId().getRoomId();
			}

			updateAutoPilotStaff(sourceATmp.getYears(), sourceATmp.getSemester(),
					sourceATmp.getCourseId().getCourseId(), sourceATmp.getCourseType(),
					sourceATmp.getGroupId().getGroupId(), sourceDay, sourceStartTime, sourceEndTime, sourceRoom);

		}

	}

	public void autoPilot(String yId, String sId) throws ParseException {

		Collection<Timetable> sourceA = timetableService.findAllByYearsAndSemester(yId, sId);
		ArrayList<Timetable> list = new ArrayList<>(sourceA);
		Integer dayA = 1;
		Long listTmpTmp = null;

		Collections.sort(list, Comparator.comparing(t -> t.getGroupId().getGroupId()));
		Collections.reverse(list);

		cleanAll(yId, sId);

		for (Timetable listTmp : list) {

			while (dayA <= 7) {

				if (listTmpTmp != null) {
					if (listTmpTmp != listTmp.getGroupId().getGroupId()) {
						dayA = 1;
					}
				}

				listTmpTmp = listTmp.getGroupId().getGroupId();

				if (listTmp.getDayOfWeek() == null && !listTmp.isTimeLocker()) {

					ArrayList<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
					Integer timeRun;
					Integer j = 0;
					DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					Time timeValueStart = null;
					Time timeValueEnd = new Time(0, 0, 0);

					if (listTmp.getCourseType() == 0) {
						timeRun = listTmp.getCourseId().getCourseLect();
					} else {
						timeRun = (listTmp.getCourseId().getCoursePerf());
					}

					targetA = new ArrayList<>(showStartTimeOptionStaff(true, listTmp.getYears(),
							listTmp.getSemester(), listTmp.getCourseId().getCourseId(), listTmp.getCourseType(),
							listTmp.getGroupId().getGroupId(), dayA));

					for (M_Timetable_ShowTimeRemain_Response targetATmp : targetA) {

						if (!targetATmp.getText().substring(0, 1).equals("!")) {
							boolean notBussy = true;
							for (int i = 1; i < timeRun; i++) {
								if ((j + i) > (targetA.size() - timeRun) || (j + i) > 10
										|| targetA.get(j + i).getText().substring(0, 1).equals("!")) {
									notBussy = false;
									break;
								}
							}
							if (notBussy) {
								timeValueStart = new Time(formatter.parse(targetATmp.getValue()).getTime());
								break;
							}
						}
						j++;
					}

					if (timeValueStart != null) {
						timeValueEnd.setHours(timeValueStart.getHours() + timeRun);
						if (timeValueStart != null && timeValueEnd.getHours() <= 18) {
							updateAutoPilotStaff(listTmp.getYears(), listTmp.getSemester(),
									listTmp.getCourseId().getCourseId(), listTmp.getCourseType(),
									listTmp.getGroupId().getGroupId(), dayA, timeValueStart, timeValueEnd,
									listTmp.getRoomId() == null ? null : listTmp.getRoomId().getRoomId());
							break;
						} else {
							dayA++;
						}
					} else {
						dayA++;
					}
				} else {
					dayA = 1;
					break;
				}

			}

		}

		for (Timetable listTmp : list) {
			if (!listTmp.isRoomLocker()) {

				Iterable<M_For_Selection_Response> targetA = new ArrayList<M_For_Selection_Response>();
				Integer timevalueStart = null;

				targetA = showRoomStaff(true, listTmp.getYears(), listTmp.getSemester(),
						listTmp.getCourseId().getCourseId(), listTmp.getCourseType(),
						listTmp.getGroupId().getGroupId(), listTmp.getDayOfWeek(), listTmp.getStartTime(),
						listTmp.getEndTime());

				if (targetA != null) {
					for (M_For_Selection_Response targetATmp : targetA) {
						System.out.println(targetATmp.getText());
						if (!targetATmp.getText().substring(0, 1).equals("!")) {
							timevalueStart = Integer.parseInt(targetATmp.getValue());
							break;
						}
					}

					updateAutoPilotStaff(listTmp.getYears(), listTmp.getSemester(),
							listTmp.getCourseId().getCourseId(), listTmp.getCourseType(),
							listTmp.getGroupId().getGroupId(), listTmp.getDayOfWeek(), listTmp.getStartTime(),
							listTmp.getEndTime(), timevalueStart);
				}
			}
		}
	}

	public Iterable<M_Timetable_ShowTable_Response> showTableTeacher(String yId, String sId, Integer dayOfWeek) {

		Collection<M_Timetable_ShowTable_Response> target = new ArrayList<M_Timetable_ShowTable_Response>();

		for (Integer index = 1, indexB = 0; index <= 14; index++) {

			Collection<Timetable> sourceA = timetableService
					.findAllByYearsAndSemesterAndMemberIdAndDayOfWeekAndStartTime(yId,
							sId, memberService.findByMemberId(getCurrentUserId()).get(), dayOfWeek, convertStartTime(index));

							

			M_Timetable_ShowTable_Response targetSub = new M_Timetable_ShowTable_Response();

			Collection<String> sourceB = new ArrayList<String>();
			Collection<String> sourceC = new ArrayList<String>();

			if (sourceA.isEmpty() == false) {

				for (Timetable sourceATmp : sourceA) {
					targetSub.setIndex(index);
					targetSub.setActiveStatus(1);
					targetSub.setCourseLect(
							sourceATmp.getCourseType() == 0 ? sourceATmp.getCourseId().getCourseLect() : 0);
					targetSub.setCoursePerf(
							sourceATmp.getCourseType() == 0 ? 0 : sourceATmp.getCourseId().getCoursePerf() / 3);
					targetSub.setTimeLect(
							sourceATmp.getCourseType() == 0 ? sourceATmp.getCourseId().getCourseLect() : 0);
					targetSub.setTimePerf(
							sourceATmp.getCourseType() == 0 ? 0 : sourceATmp.getCourseId().getCoursePerf());
					targetSub.setCourse_code(sourceATmp.getCourseId().getCourse_code());
					targetSub.setCourse_title(sourceATmp.getCourseId().getCourse_title());
					targetSub.setDay_of_week(sourceATmp.getDayOfWeek());

					if (sourceB.isEmpty()) {
						targetSub.setGroup_name(sourceATmp.getGroupId().getGroup_name());
						sourceB.add(sourceATmp.getGroupId().getGroup_name());
					} else if (!sourceB.contains(sourceATmp.getGroupId().getGroup_name())) {
						targetSub.setGroup_name(targetSub.getGroup_name() + ", " +sourceATmp.getGroupId().getGroup_name());
						sourceB.add(sourceATmp.getGroupId().getGroup_name());
					}

					Collection<Timetable> sourceD = timetableService.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(sourceATmp.getYears(),
					sourceATmp.getSemester(), sourceATmp.getCourseId(), sourceATmp.getCourseType(), sourceATmp.getGroupId());
					
					if(sourceC.isEmpty()){
						targetSub.setMember_name(
								sourceATmp.getMemberId().getTitleId().getTitleShort().toString() + " "
								+ sourceATmp.getMemberId().getThFirstName().toString() + " "
								+ sourceATmp.getMemberId().getThLastName().toString() );
						sourceC.add(sourceATmp.getMemberId().getThFirstName().toString() + sourceATmp.getMemberId().getThLastName().toString());
					} 
					
					for(Timetable sourceDTmp : sourceD){
						if (!sourceC.contains(sourceDTmp.getMemberId().getThFirstName().toString() + sourceATmp.getMemberId().getThLastName().toString())) {
							targetSub.setMember_name(
									targetSub.getMember_name() + ", " 
									+ sourceDTmp.getMemberId().getTitleId().getTitleShort().toString() + " "
									+ sourceDTmp.getMemberId().getThFirstName().toString() + " "
									+ sourceDTmp.getMemberId().getThLastName().toString());
							sourceC.add(sourceDTmp.getMemberId().getThFirstName().toString() + sourceDTmp.getMemberId().getThLastName().toString());
						}
					}

					targetSub.setRoom_name(sourceATmp.getRoomId().getRoomName());
					indexB = sourceATmp.getCourseType() == 0 ? sourceATmp.getCourseId().getCourseLect() - 1 : sourceATmp.getCourseId().getCoursePerf() - 1;
				}

			} else if (indexB != 0) {
				targetSub.setIndex(index);
				targetSub.setActiveStatus(2);
				indexB--;
			} else {
				targetSub.setIndex(index);
				targetSub.setActiveStatus(3);

			}
			target.add(targetSub);
		}

		return target;
	}

	public Iterable<M_Timetable_ShowTable_Response> showTableStaff(String yId, String sId,
			Integer memberId, Integer dayOfWeek) {

		Collection<M_Timetable_ShowTable_Response> target = new ArrayList<M_Timetable_ShowTable_Response>();

		for (Integer index = 1, indexB = 0; index <= 14; index++) {

			Collection<Timetable> sourceA = timetableService
					.findAllByYearsAndSemesterAndMemberIdAndDayOfWeekAndStartTime(yId,
							sId, memberService.findByMemberId(memberId).get(), dayOfWeek, convertStartTime(index));

							

			M_Timetable_ShowTable_Response targetSub = new M_Timetable_ShowTable_Response();

			Collection<String> sourceB = new ArrayList<String>();
			Collection<String> sourceC = new ArrayList<String>();

			if (sourceA.isEmpty() == false) {

				for (Timetable sourceATmp : sourceA) {
					targetSub.setIndex(index);
					targetSub.setActiveStatus(1);
					targetSub.setCourseLect(
							sourceATmp.getCourseType() == 0 ? sourceATmp.getCourseId().getCourseLect() : 0);
					targetSub.setCoursePerf(
							sourceATmp.getCourseType() == 0 ? 0 : sourceATmp.getCourseId().getCoursePerf() / 3);
					targetSub.setTimeLect(
							sourceATmp.getCourseType() == 0 ? sourceATmp.getCourseId().getCourseLect() : 0);
					targetSub.setTimePerf(
							sourceATmp.getCourseType() == 0 ? 0 : sourceATmp.getCourseId().getCoursePerf());
					targetSub.setCourse_code(sourceATmp.getCourseId().getCourse_code());
					targetSub.setCourse_title(sourceATmp.getCourseId().getCourse_title());
					targetSub.setDay_of_week(sourceATmp.getDayOfWeek());

					if (sourceB.isEmpty()) {
						targetSub.setGroup_name(sourceATmp.getGroupId().getGroup_name());
						sourceB.add(sourceATmp.getGroupId().getGroup_name());
					} else if (!sourceB.contains(sourceATmp.getGroupId().getGroup_name())) {
						targetSub.setGroup_name(targetSub.getGroup_name() + ", " +sourceATmp.getGroupId().getGroup_name());
						sourceB.add(sourceATmp.getGroupId().getGroup_name());
					}

					Collection<Timetable> sourceD = timetableService.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(sourceATmp.getYears(),
					sourceATmp.getSemester(), sourceATmp.getCourseId(), sourceATmp.getCourseType(), sourceATmp.getGroupId());
					
					if(sourceC.isEmpty()){
						targetSub.setMember_name(
								sourceATmp.getMemberId().getTitleId().getTitleShort().toString() + " "
								+ sourceATmp.getMemberId().getThFirstName().toString() + " "
								+ sourceATmp.getMemberId().getThLastName().toString() );
						sourceC.add(sourceATmp.getMemberId().getThFirstName().toString() + sourceATmp.getMemberId().getThLastName().toString());
					} 
					
					for(Timetable sourceDTmp : sourceD){
						if (!sourceC.contains(sourceDTmp.getMemberId().getThFirstName().toString() + sourceATmp.getMemberId().getThLastName().toString())) {
							targetSub.setMember_name(
									targetSub.getMember_name() + ", " 
									+ sourceDTmp.getMemberId().getTitleId().getTitleShort().toString() + " "
									+ sourceDTmp.getMemberId().getThFirstName().toString() + " "
									+ sourceDTmp.getMemberId().getThLastName().toString());
							sourceC.add(sourceDTmp.getMemberId().getThFirstName().toString() + sourceDTmp.getMemberId().getThLastName().toString());
						}
					}

					targetSub.setRoom_name(sourceATmp.getRoomId().getRoomName());
					indexB = sourceATmp.getCourseType() == 0 ? sourceATmp.getCourseId().getCourseLect() - 1 : sourceATmp.getCourseId().getCoursePerf() - 1;
				}

			} else if (indexB != 0) {
				targetSub.setIndex(index);
				targetSub.setActiveStatus(2);
				indexB--;
			} else {
				targetSub.setIndex(index);
				targetSub.setActiveStatus(3);

			}
			target.add(targetSub);
		}

		return target;
	}

	// -543
	public void createTeacher(M_Timetable_CreateTeacher_Request request) {
		timetableService.create(request.getYears().toString(), request.getSemester(),
				courseService.findByCourseId(request.getCourseId()).get(),
				request.getCourseType(),
				groupService.findByGroupId(request.getGroupId()).get(),
				memberService.findByMemberId(getCurrentUserId()).get());
	}

	public void createStaff(M_Timetable_CreateStaff_Request request) {
		timetableService.create(request.getYears().toString(), request.getSemester(),
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
			Integer day, Time start, Time end, Integer room) {

		String year = yId;
		String semeter = sId;
		Course courseId = courseService.findByCourseId(cId).get();
		Group groupId = groupService.findByGroupId(gId).get();
		Integer dayOfWeek = day;
		Time startTime = start;
		Time endTime = end;
		Room roomId = roomService.findAllByRoomId(room).isPresent() == false ? null // @todo check point
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

		Collection<ReplaceTeach> sourceA = replaceTeachService
				.findAllByEssTimetableId(timetableService.findByTimetableId(timetableId).get());

		for (ReplaceTeach sourceATmp : sourceA) {
			replaceTeachService.deleteByLeaveTeachId(
					leaveTeachService.findByLeaveTeachId(sourceATmp.getLeaveTeachId().getLeaveTeachId()));
			leaveTeachService.delete(sourceATmp.getLeaveTeachId().getLeaveTeachId());
		}

		timetableService.delete(timetableId);
	}

	public void deleteForPlanTeacher(String yId, String sId, Long cId, Integer courseType, Long gId) {

		Collection<Timetable> sourceA = timetableService
				.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndMemberId(yId,
						sId, courseService.findByCourseId(cId).get(), courseType,
						groupService.findByGroupId(gId).get(), memberService.findByMemberId(getCurrentUserId()).get());

		for (Timetable sourceATmp : sourceA) {

			Collection<ReplaceTeach> sourceB = replaceTeachService.findAllByEssTimetableId(sourceATmp);

			for (ReplaceTeach sourceBTmp : sourceB) {
				replaceTeachService.deleteByLeaveTeachId(
						leaveTeachService.findByLeaveTeachId(sourceBTmp.getLeaveTeachId().getLeaveTeachId()));
				leaveTeachService.delete(sourceBTmp.getLeaveTeachId().getLeaveTeachId());
			}
		}

		timetableService.deleteForPlan(yId, sId, courseService.findByCourseId(cId).get(), courseType,
				groupService.findByGroupId(gId).get(), memberService.findByMemberId(getCurrentUserId()).get());
	}

	public void deleteForPlanStaff(String yId, String sId, Long cId, Integer courseType, Long gId, Integer memberId) {

		Collection<Timetable> sourceA = timetableService
				.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndMemberId(yId,
						sId, courseService.findByCourseId(cId).get(), courseType,
						groupService.findByGroupId(gId).get(), memberService.findByMemberId(memberId).get());

		for (Timetable sourceATmp : sourceA) {

			Collection<ReplaceTeach> sourceB = replaceTeachService.findAllByEssTimetableId(sourceATmp);

			for (ReplaceTeach sourceBTmp : sourceB) {
				replaceTeachService.deleteByLeaveTeachId(
						leaveTeachService.findByLeaveTeachId(sourceBTmp.getLeaveTeachId().getLeaveTeachId()));
				leaveTeachService.delete(sourceBTmp.getLeaveTeachId().getLeaveTeachId());
			}
		}

		timetableService.deleteForPlan(yId, sId, courseService.findByCourseId(cId).get(), courseType,
				groupService.findByGroupId(gId).get(), memberService.findByMemberId(memberId).get());
	}

	// Utill

	public Time convertStartTime(Integer input) {
		Time output = java.sql.Time.valueOf("08:00:00");
		switch (input) {
			case 1:
				output = java.sql.Time.valueOf("08:00:00");
				break;
			case 2:
				output = java.sql.Time.valueOf("09:00:00");
				break;
			case 3:
				output = java.sql.Time.valueOf("10:00:00");
				break;
			case 4:
				output = java.sql.Time.valueOf("11:00:00");
				break;
			case 5:
				output = java.sql.Time.valueOf("12:00:00");
				break;
			case 6:
				output = java.sql.Time.valueOf("13:00:00");
				break;
			case 7:
				output = java.sql.Time.valueOf("14:00:00");
				break;
			case 8:
				output = java.sql.Time.valueOf("15:00:00");
				break;
			case 9:
				output = java.sql.Time.valueOf("16:00:00");
				break;
			case 10:
				output = java.sql.Time.valueOf("17:00:00");
				break;
			case 11:
				output = java.sql.Time.valueOf("18:00:00");
				break;
			case 12:
				output = java.sql.Time.valueOf("19:00:00");
				break;
			case 13:
				output = java.sql.Time.valueOf("20:00:00");
				break;
			case 14:
				output = java.sql.Time.valueOf("21:00:00");
				break;
		}
		return output;
	}

}
