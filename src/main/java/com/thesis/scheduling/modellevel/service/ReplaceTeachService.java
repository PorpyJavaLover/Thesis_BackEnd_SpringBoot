package com.thesis.scheduling.modellevel.service;

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

	// SET
	public void create(LeaveTeach leaveTeachId, Timetable essTimetableId, Member memberReplaceId) {

		Optional<ReplaceTeach> opt = repository.findByLeaveTeachIdAndEssTimetableIdAndMemberReplaceId(leaveTeachId,
				essTimetableId, memberReplaceId);
		ReplaceTeach entity = new ReplaceTeach();

		if (!opt.isEmpty()) {
			entity = opt.get();
		} else {
			entity.setLeaveTeachId(leaveTeachId);
			entity.setEssTimetableId(essTimetableId);
			entity.setMemberReplaceId(memberReplaceId);
		}
		repository.save(entity);
	}

	// DELETE
	public void delete(int replaceTeachId) {
		Optional<ReplaceTeach> opt = repository.findByReplaceTeachId(replaceTeachId);
		ReplaceTeach entity = new ReplaceTeach();

		if (!opt.isEmpty()) {
			entity = opt.get();
			repository.delete(entity);
		}
	}
}
