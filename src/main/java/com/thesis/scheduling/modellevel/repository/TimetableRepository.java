package com.thesis.scheduling.modellevel.repository;

import java.sql.Time;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.entity.Timetable;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, String> {

	Collection<Timetable> findAll();

	Collection<Timetable> findAllByYearsAndSemester(String years, String semester);

	Collection<Timetable> findAllByYearsAndSemesterAndMemberId(String years, String semester, Member memberId);

	Collection<Timetable> findAllByMemberId(Member memberId);

	Collection<Timetable> findAllByMemberIdAndDayOfWeek(Member memberId, Integer dayOfWeek);

	Collection<Timetable> findAllByMemberIdAndYearsAndDayOfWeek(Member memberId, String years, Integer dayOfWeek);

	Collection<Timetable> findAllByAndMemberIdAndYearsAndSemesterAndDayOfWeek(Member memberId, String years,
			String semester, Integer dayOfWeek);

	Collection<Timetable> findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(String years, String semester,
			Course courseId, Integer courseType, Group groupId);

	Collection<Timetable> findAllByYearsAndSemesterAndGroupIdAndDayOfWeek(String years, String semester, Group groupId,
			Integer dayOfWeek);

	Collection<Timetable> findAllByYearsAndSemesterAndCourseIdAndGroupIdAndMemberId(String years, String semester,
			Course courseId, Group groupId, Member memberId);

	Collection<Timetable> findAllByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndMemberId(String years,
			String semester,
			Course courseId, Integer courseType, Group groupId, Member memberId);

	Collection<Timetable> findAllByDayOfWeekAndStartTimeAndEndTime(Integer dayOfWeek, Time startTime, Time endTime);

	Collection<Timetable> findAllByDayOfWeek(Integer dayOfWeek);

	Collection<Timetable> findAllByRoomId(Room roomId);

	Collection<Timetable> findAllByYearsAndSemesterAndMemberIdAndDayOfWeekAndStartTime(String years, String semester,
			Member memberId, Integer dayOfWeek, Time startTime);

	Optional<Timetable> findByYearsAndSemesterAndCourseIdAndGroupId(String years, String semester,
			Course courseId, Group groupId);

	Optional<Timetable> findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupId(String years, String semester,
			Course courseId, Integer courseType, Group groupId);

	Optional<Timetable> findByYearsAndSemesterAndCourseIdAndGroupIdAndCourseTypeAndMemberId(String years,
			String semester,
			Course courseId, Group groupId, Integer courseType, Member memberId);

	Optional<Timetable> findByTimetableId(int timetableId);

	Collection<Timetable> findByYearsAndSemesterAndCourseIdAndCourseTypeAndGroupIdAndDayOfWeek(String years,
			String semester,
			Course courseId, Integer courseType, Group groupId, Integer dayOfWeek);

}
