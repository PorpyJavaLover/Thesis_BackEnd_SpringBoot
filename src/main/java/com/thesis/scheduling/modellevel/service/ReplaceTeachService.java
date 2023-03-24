package com.thesis.scheduling.modellevel.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.repository.ReplaceTeachRepository;

@Service
public class ReplaceTeachService {

	private final ReplaceTeachRepository repository;

	public ReplaceTeachService(ReplaceTeachRepository repository) {
		this.repository = repository;
	}

	// GET
	public Iterable<ReplaceTeach> showAllByMemberId(Member memberId) {
		return repository.findAllByMemberReplaceId(memberId);
	}

	public Iterable<ReplaceTeach> showAll() {
		return repository.findAll();
	}

	public Collection<ReplaceTeach> findAllByLeaveTeachId(LeaveTeach leaveTeachId) {
		return repository.findAllByLeaveTeachId(leaveTeachId);
	}

	// SET
	public void create(LeaveTeach leaveTeachId, Timetable essTimetableId , Date date ) {

		ReplaceTeach entity = new ReplaceTeach();

		entity.setLeaveTeachId(leaveTeachId);
		entity.setEssTimetableId(essTimetableId);
		entity.setMemberReplaceId(null);
		entity.setDate(date);

		repository.save(entity);
	}

	public void updateTeacher(int replaceTeachId , Member memberId) {

		ReplaceTeach entity = repository.findByReplaceTeachId(replaceTeachId).get();

		entity.setMemberReplaceId(memberId);

		repository.save(entity);
	}

	// DELETE
	public void delete(int replaceTeachId) {
		Optional<ReplaceTeach> opt = repository.findByReplaceTeachId(replaceTeachId);
		ReplaceTeach entity = new ReplaceTeach();

		if (opt.isPresent()) {
			entity = opt.get();
			repository.delete(entity);
		}
	}

	public void deleteByLeaveTeachId(LeaveTeach leaveTeachId) {
		Collection<ReplaceTeach> sourceReplaceTeach = findAllByLeaveTeachId(leaveTeachId);
		if (sourceReplaceTeach != null) {
			for (ReplaceTeach subReplaceTeach : sourceReplaceTeach) {
				ReplaceTeach entity = new ReplaceTeach();
				entity = subReplaceTeach;
				repository.delete(entity);
			}
		}
	}
}
