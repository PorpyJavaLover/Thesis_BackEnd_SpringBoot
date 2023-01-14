package com.thesis.scheduling.modellevel.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Plan;
import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowTimeRemain_Response;
import com.thesis.scheduling.modellevel.repository.TimetableRepository;

@Service
public class TimetableService {

	private final TimetableRepository repository;

	public TimetableService(TimetableRepository repository) {
		this.repository = repository;
	}

	// GET
	public Iterable<Timetable> findAll() {
		return repository.findAll();
	}

	public Iterable<Timetable> findAllByRoomId(Room roomId) {
		return repository.findAllByRoomId(roomId);
	}

	public Iterable<Timetable> findByYearsAndSemesterAndCourseIdAndGroupIdAndMemberId(String years, String semester,
			Course courseId, Group groupId, Member memberId) {
		return repository.findAllByYearsAndSemesterAndCourseIdAndGroupIdAndMemberId(years, semester, courseId, groupId,
				memberId);
	}

	public Iterable<Timetable> findAllByMemberId(Member memberId) {
		return repository.findAllByMemberId(memberId);
	}

	public Iterable<Timetable> findAllByMemberIdAndDayOfWeek(Member memberId, Integer dayOfWeek) {
		return repository.findAllByMemberIdAndDayOfWeek(memberId, dayOfWeek);
	}

	public Iterable<Timetable> findAllByCollectionMemberAndDayOfWeek(Iterable<Member> memberId, Integer dayOfWeek) {

		Collection<Timetable> targetA = new ArrayList<Timetable>();

		for (Member memberIdSub : memberId) {
			Collection<Timetable> targetB = repository.findAllByMemberIdAndDayOfWeek(memberIdSub, dayOfWeek);
			targetA.addAll(targetB);
		}

		return targetA;
	}

	public Iterable<Member> findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupId(String years,
			String semester, Course courseId, Integer courseType, Group groupId) {

		if (years == null || semester == null || courseId == null || courseType == null || groupId == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years, semester,
				courseId, courseType, groupId);

		Collection<Member> targetB = new ArrayList<Member>();

		for (Timetable targetASub : targetA) {
			Member targetC = targetASub.getMemberId();
			targetB.add(targetC);
		}

		return targetB;

	}

	public Iterable<Timetable> findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupIdAndDayOfWeek(String years,
			String semester, Course courseId, Integer courseType, Group groupId, Integer dayOfWeek) {

		if (years == null || semester == null || courseId == null || courseType == null || groupId == null || dayOfWeek == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years, semester,
				courseId, courseType, groupId);

		Collection<Timetable> targetB = new ArrayList<Timetable>();

		for (Timetable targetASub : targetA) {
			Collection<Timetable> targetC = repository.findAllByMemberIdAndDayOfWeek(targetASub.getMemberId(),
					dayOfWeek);
			targetB.addAll(targetC);
		}

		Collection<Timetable> targetD = relativeComplementBInA(targetB, targetA);

		return targetD;

	}

	public Iterable<Timetable> findAllCollectionRoomByDayOfWeek(String years, String semester, Course courseId, Integer courseType,
			Group groupId, Integer dayOfWeek, Time startTime, Time endTime) {

		if (endTime == null || startTime == null || dayOfWeek == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findAllByDayOfWeek(dayOfWeek);

		Collection<Timetable> targetB = new ArrayList<Timetable>();

		for (Timetable targetASub : targetA) {

			if (targetASub.getRoomId() != null) {
				if (targetASub.getEndTime().getHours() > startTime.getHours()
						&& targetASub.getStartTime().getHours() < endTime.getHours()) {
					targetB.add(targetASub);
				}
			}
		}

		Collection<Timetable> targetC = repository.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years, semester,
				courseId, courseType, groupId);

		Collection<Timetable> targetD = relativeComplementBInA(targetB, targetC);

		return targetD;

	}

	// SET
	public void create(String years, String semester, Course courseId, Integer courseType, Group groupId,
			Member memberId) {

		Timetable entity = new Timetable();

		Optional<Timetable> optA = repository.findByYearsAndSemesterAndCourseIdAndGroupIdAndCourseTypeAndMemberId(years,
				semester, courseId, groupId, courseType, memberId);

		if (!optA.isEmpty()) {
			entity = optA.get();
		} else {



			entity.setYears(years);
			entity.setSemester(semester);
			entity.setCourseId(courseId);
			entity.setGroupId(groupId);
			entity.setCourseType(courseType);
			entity.setMemberId(memberId);
			
			Collection<Timetable> optB = repository.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
					semester, courseId, courseType, groupId);

			if (!optB.isEmpty()) {

				Timetable optBSub = optB.iterator().next();
				entity.setDayOfWeek(optBSub.getDayOfWeek() == null ? null : optBSub.getDayOfWeek());
				entity.setStartTime(optBSub.getStartTime() == null ? null : optBSub.getStartTime());
				entity.setEndTime(optBSub.getEndTime() == null ? null : optBSub.getEndTime());
				entity.setRoomId(optBSub.getRoomId() == null ? null : optBSub.getRoomId());
				entity.setTimeLocker(optBSub.isTimeLocker());
				entity.setRoomLocker(optBSub.isRoomLocker());
			}

			repository.save(entity);

		}

	}

	public void updateStaff(String years, String semester, Course courseId, Integer courseType, Group groupId,
			Integer day_of_week, Time start_time, Time end_time, Room room_id) {

		if (years == null || semester == null || courseId == null || courseType == null || groupId == null) {
			throw new IllegalArgumentException("One or more parameters are empty");
		}

		Iterable<Timetable> targetA = repository.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester, courseId, courseType, groupId);

		for (Timetable targetASub : targetA) {
			if (day_of_week != null) {
				targetASub.setDayOfWeek(day_of_week);
			}
			if (start_time != null) {
				targetASub.setStartTime(start_time);
			}
			if (end_time != null) {
				targetASub.setEndTime(end_time);
			}
			if (room_id != null) {
				targetASub.setRoomId(room_id);
			}
			repository.save(targetASub);
		}

	}

	public void updateLockerStaff(String year, String semeter, Course courseId, Integer courseType, Group groupId, boolean timeLocker,
			boolean roomLocker) {

		if (year == null || semeter == null || courseId == null || groupId == null) {
			throw new IllegalArgumentException("One or more parameters are empty in method updateLockerStaff");
		}

		Iterable<Timetable> targetA = repository.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(year, semeter, courseId, courseType,
				groupId);

		for (Timetable targetASub : targetA) {
			targetASub.setTimeLocker(timeLocker);
			targetASub.setRoomLocker(roomLocker);
			repository.save(targetASub);
		}

	}

	// DELETE
	public void delete(int timetableId) {

		Timetable entity = new Timetable();

		Optional<Timetable> opt = repository.findByTimetableId(timetableId);

		if (!opt.isEmpty()) {
			entity = opt.get();
			repository.delete(entity);
		}

	}

	public void deleteForPlan(String years, String semester, Course courseId, Integer courseType , Group groupId, Member memberId) {

		Iterable<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndMemberId(years,
				semester, courseId, courseType, groupId, memberId);

		for (Timetable targetASub : targetA) {
			repository.delete(targetASub);
		}

	}

	// util function

	public Collection<Timetable> relativeComplementBInA(Collection<Timetable> cA, Collection<Timetable> cB) {

		Collection<Timetable> result = new ArrayList<Timetable>();

		cA.removeAll(cB);

		result.addAll(cA);

		return result;
	}

}
