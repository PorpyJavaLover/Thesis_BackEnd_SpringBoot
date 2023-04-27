package com.thesis.scheduling.modellevel.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.repository.LeaveTeachRepository;

@Service
public class LeaveTeachService {

	private final LeaveTeachRepository repository;

	public LeaveTeachService(LeaveTeachRepository repository) {
		this.repository = repository;
	}

	// GET
	public Iterable<LeaveTeach> showAllByMemberId(Member memberId) {
		return repository.findAllByMemberId(memberId);
	}

	public Iterable<LeaveTeach> findAllByYearsAndSemesterAndMemberId(String year, String semester, Member memberId) {
		return repository.findAllByYearsAndSemesterAndMemberId(year, semester, memberId);
	}

	public Iterable<LeaveTeach> showAll() {
		return repository.findAll();
	}

	public LeaveTeach findByLeaveTeachId(int leaveTeachId) {
		return repository.findByLeaveTeachId(leaveTeachId).get();
	}

	// SET
	public LeaveTeach create(Member memberId, String years, String semester, Date dateStart, Date dateEnd,
			String note) {

		Optional<LeaveTeach> opt = repository.findByMemberIdAndYearsAndSemesterAndDateStartAndDateEnd(memberId, years,
				semester, dateStart, dateEnd);
		LeaveTeach entity = new LeaveTeach();

		if (opt.isPresent()) {
			entity = opt.get();
		} else {
			entity.setMemberId(memberId);
			entity.setYears(years);
			entity.setSemester(semester);
			entity.setDateStart(dateStart);
			entity.setDateEnd(dateEnd);
			entity.setNote(note);
		}
		repository.save(entity);

		return entity;
	}

	public void update(int leaveTeachId, String years, String semester, Date dateStart, Date dateEnd,
			String note) {

		Optional<LeaveTeach> opt = repository.findByLeaveTeachId(leaveTeachId);
		LeaveTeach entity = new LeaveTeach();

		if (opt.isPresent()) {
			entity = opt.get();
			entity.setYears(years);
			entity.setSemester(semester);
			entity.setDateStart(dateStart);
			entity.setDateEnd(dateEnd);
			entity.setNote(note);
			repository.save(entity);
		}

	}

	// DELETE
	public void delete(int leaveTeachId) {
		Optional<LeaveTeach> opt = repository.findByLeaveTeachId(leaveTeachId);
		LeaveTeach entity = new LeaveTeach();

		if (opt.isPresent()) {
			entity = opt.get();
			repository.delete(entity);
		}
	}

}
