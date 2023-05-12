package com.thesis.scheduling.modellevel.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.entity.Timetable;

@Repository
public interface ReplaceTeachRepository extends CrudRepository<ReplaceTeach, String> {

	Optional<ReplaceTeach> findByReplaceTeachId(int replaceTeachId);

	Collection<ReplaceTeach> findAllByMemberReplaceId(Member memberReplaceId);

	Collection<ReplaceTeach> findAllByEssTimetableId(Timetable essTimetableId);

	Collection<ReplaceTeach> findAllByLeaveTeachId(LeaveTeach leaveTeachId);

	Collection<ReplaceTeach> findAllByLeaveTeachIdAndMemberReplaceId(LeaveTeach leaveTeachId,Member memberReplaceId);

	Optional<ReplaceTeach> findByLeaveTeachIdAndEssTimetableIdAndMemberReplaceId(LeaveTeach leaveTeachId,
			Timetable essTimetableId, Member memberReplaceId);
	
	

}
