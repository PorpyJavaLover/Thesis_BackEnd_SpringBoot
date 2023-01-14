package com.thesis.scheduling.modellevel.repository;

import java.sql.Time;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.NotTeach;


@Repository
public interface NotTeachRepository extends CrudRepository<NotTeach,String> {
	
	Collection<NotTeach> findAllByMemberId(Member memberId);
	
	Collection<NotTeach> findAllByMemberIdAndDayOfWeek(Member memberId , Integer dayOfWeek);

	Optional<NotTeach> findByMemberIdAndDayOfWeekAndTimeStartAndTimeEnd(Member memberId, Integer dayOfWeek , Time timeStart , Time timeEnd);

	Optional<NotTeach> findByNotId(int notId);
	
}
