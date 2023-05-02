package com.thesis.scheduling.modellevel.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.repository.TimetableRepository;

@Service
public class TimetableService {

	private final TimetableRepository repository;

	public TimetableService(TimetableRepository repository) {
		this.repository = repository;
	}

	// GET
	public Collection<Timetable> findAll() {
		return repository.findAll();
	}

	public Optional<Timetable> findByTimetableId(int timetableId) {
		return repository.findByTimetableId(timetableId);
	}

	public Collection<Timetable> findAllByYearsAndSemester(String years, String semester) {
		return repository.findAllByYearsAndSemester(years, semester);
	}

	public Collection<Timetable> findAllByYearsAndSemesterAndMemberId(String years, String semester, Member memberId) {
		return repository.findAllByYearsAndSemesterAndMemberId(years, semester, memberId);
	}

	public Iterable<Timetable> findAllByRoomId(Room roomId) {
		return repository.findAllByRoomId(roomId);
	}

	public Iterable<Timetable> findByYearsAndSemesterAndCourseIdAndGroupIdAndMemberId(String years, String semester,
			Course courseId, Group groupId, Member memberId) {
		return repository.findAllByYearsAndSemesterAndCourseIdAndGroupIdAndMemberId(years, semester, courseId, groupId,
				memberId);
	}

	public Collection<Timetable> findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(String years,
			String semester, Course courseId, Integer courseType, Group groupId) {
		return repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester, courseId, courseType, groupId);
	}

	public Collection<Timetable> findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndMemberId(String years,
			String semester, Course courseId, Integer courseType, Group groupId, Member memberId) {
		return repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndMemberId(years,
				semester, courseId, courseType, groupId, memberId);
	}

	public Collection<Timetable> findAllByMemberId(Member memberId) {
		return repository.findAllByMemberId(memberId);
	}

	public Iterable<Timetable> findAllByMemberIdAndDayOfWeek(Member memberId, Integer dayOfWeek) {
		return repository.findAllByMemberIdAndDayOfWeek(memberId, dayOfWeek);
	}

	public Collection<Timetable> findAllByAndMemberIdAndYearsAndSemesterAndDayOfWeek(Member memberId, String years,
			String semester, Integer dayOfWeek) {

		if (memberId == null || years == null || semester == null || dayOfWeek == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findAllByAndMemberIdAndYearsAndSemesterAndDayOfWeek(memberId, years,
				semester, dayOfWeek);

		if (!targetA.isEmpty()) {
			return targetA;
		} else {
			return null;
		}
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

		Collection<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester, courseId, courseType, groupId);

		Collection<Member> targetB = new ArrayList<Member>();

		for (Timetable targetASub : targetA) {
			Member targetC = targetASub.getMemberId();
			targetB.add(targetC);
		}

		return targetB;

	}

	public Timetable findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(String years,
			String semester, Course courseId, Integer courseType, Group groupId) {

		if (years == null || semester == null || courseId == null || courseType == null || groupId == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester, courseId, courseType, groupId);

		if (!targetA.isEmpty()) {
			return targetA.iterator().next();
		} else {
			return null;
		}
	}

	public Integer getCourseType(String years,
			String semester, Course courseId, Integer courseType, Group groupId) {

		if (years == null || semester == null || courseId == null || courseType == null || groupId == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester, courseId, courseType, groupId);

		if (!targetA.isEmpty()) {
			int x;
			Timetable subTargetA = targetA.iterator().next();
			if (subTargetA.getCourseType() == 0) {
				x = subTargetA.getCourseId().getCourseLect();
			} else {
				x = (subTargetA.getCourseId().getCoursePerf());
			}
			return x;
		} else {
			return null;
		}
	}

	public Collection<Timetable> findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndDayOfWeek(String years,
			String semester, Course courseId, Integer courseType, Group groupId, Integer dayOfWeek) {

		if (years == null || semester == null || courseId == null || courseType == null || groupId == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndDayOfWeek(
				years,
				semester, courseId, courseType, groupId, dayOfWeek);

		if (!targetA.isEmpty()) {
			return targetA;
		} else {
			return null;
		}
	}

	public Iterable<Timetable> findAllCollectionMemberByYearsAndSemesterAndCourseIdAndGroupIdAndDayOfWeek(String years,
			String semester, Course courseId, Integer courseType, Group groupId, Integer dayOfWeek) {

		if (years == null || semester == null || courseId == null || courseType == null || groupId == null
				|| dayOfWeek == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester,
				courseId, courseType, groupId);

		Collection<Timetable> targetB = new ArrayList<Timetable>();

		for (Timetable targetASub : targetA) {
			Collection<Timetable> targetC = repository.findAllByMemberIdAndYearsAndDayOfWeek(targetASub.getMemberId(),
					years, dayOfWeek);
			targetB.addAll(targetC);
		}

		Collection<Timetable> targetD = relativeComplementBInA(targetB, targetA);

		return targetD;

	}

	public Collection<Timetable> findAllCollectionMemberBGroupIdAndDayOfWeek(String years,
			String semester, Group groupId, Integer dayOfWeek) {

		if (years == null || semester == null || groupId == null || dayOfWeek == null) {
			throw new IllegalArgumentException("No values can be null");
		}

		Collection<Timetable> targetA = repository.findAllByYearsAndSemesterAndGroupIdAndDayOfWeek(years, semester,
				groupId, dayOfWeek);

		return targetA;

	}

	public Iterable<Timetable> findAllCollectionRoomByDayOfWeek(String years, String semester, Course courseId,
			Integer courseType,
			Group groupId, Integer dayOfWeek, Time startTime, Time endTime) {

		if (endTime == null || startTime == null || dayOfWeek == null) {
			return null;
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

		Collection<Timetable> targetC = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester,
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

		if (optA.isPresent()) {
			entity = optA.get();
		} else {

			entity.setYears(years);
			entity.setSemester(semester);
			entity.setCourseId(courseId);
			entity.setGroupId(groupId);
			entity.setCourseType(courseType);
			entity.setMemberId(memberId);

			Collection<Timetable> optB = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
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

		Iterable<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(years,
				semester, courseId, courseType, groupId);

		for (Timetable targetASub : targetA) {

			targetASub.setDayOfWeek(day_of_week);

			targetASub.setStartTime(start_time);

			targetASub.setEndTime(end_time);

			targetASub.setRoomId(room_id);

			repository.save(targetASub);
		}

	}

	public void updateLockerStaff(String year, String semeter, Course courseId, Integer courseType, Group groupId,
			boolean timeLocker, boolean roomLocker) {

		if (year == null || semeter == null || courseId == null || groupId == null) {
			throw new IllegalArgumentException("One or more parameters are empty in method updateLockerStaff");
		}

		Iterable<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(year,
				semeter,
				courseId, courseType,
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

		if (opt.isPresent()) {
			entity = opt.get();
			repository.delete(entity);
		}

	}

	public void deleteForPlan(String years, String semester, Course courseId, Integer courseType, Group groupId,
			Member memberId) {

		Iterable<Timetable> targetA = repository.findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndMemberId(
				years,
				semester, courseId, courseType, groupId, memberId);

		for (Timetable targetASub : targetA) {
			repository.delete(targetASub);
		}

	}

	// Util function
	public Collection<Timetable> relativeComplementBInA(Collection<Timetable> cA, Collection<Timetable> cB) {

		Collection<Timetable> result = new ArrayList<Timetable>();

		cA.removeAll(cB);

		result.addAll(cA);

		return result;
	}

}
