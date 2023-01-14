package com.thesis.scheduling.modellevel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Member;

@Repository
public interface LeaveTeachRepository extends CrudRepository<LeaveTeach, String> {
	
	Iterable<LeaveTeach> findAllByMemberId(Member memberId);

	Optional<LeaveTeach> findByMemberIdAndYearsAndSemesterAndDateStartAndDateEnd(Member memberId, String years,
			String semester, java.sql.Date dateStart, java.sql.Date dateEnd);

	Optional<LeaveTeach> findByLeaveTeachId(int leaveTeachId);
}
