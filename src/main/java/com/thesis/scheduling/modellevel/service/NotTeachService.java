package com.thesis.scheduling.modellevel.service;

import java.sql.Time;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.NotTeach;
import com.thesis.scheduling.modellevel.repository.NotTeachRepository;

@Service
public class NotTeachService {

	private final NotTeachRepository repository;

	public NotTeachService(NotTeachRepository repository) {
		this.repository = repository;
	}

	// GET
	public Collection<NotTeach> showAllByMemberId(Member memberId) {
		return repository.findAllByMemberId(memberId);
	}
	
	public Collection<NotTeach> findAllByMemberIdAndDayOfWeek(Member memberId , Integer dayOfWeek) {
		return repository.findAllByMemberIdAndDayOfWeek(memberId , dayOfWeek);
	}

	public Iterable<NotTeach> showAll() {
		return repository.findAll();
	}

	// SET
	public void create(Member memberId, int dayOfWeek, Time timeStart, Time timeEnd) {

		NotTeach entity = new NotTeach();

		Optional<NotTeach> opt = repository.findByMemberIdAndDayOfWeekAndTimeStartAndTimeEnd(memberId, dayOfWeek,
				timeStart, timeEnd);

<<<<<<< Updated upstream
		if (opt.isPresent()) {
=======
		if (!opt.isPresent()) {
>>>>>>> Stashed changes
			entity = opt.get();

		} else {
			entity.setMemberId(memberId);
			entity.setDayOfWeek(dayOfWeek);
			entity.setTimeEnd(timeEnd);
			entity.setTimeStart(timeStart);
			repository.save(entity);
		}

	}
	
	public void update(int notId , int dayOfWeek, Time timeStart, Time timeEnd) {

		Optional<NotTeach> opt = repository.findByNotId(notId);
		NotTeach entity = new NotTeach();

<<<<<<< Updated upstream
		if (opt.isPresent()) {
=======
		if (!opt.isPresent()) {
>>>>>>> Stashed changes
			entity = opt.get();
			entity.setDayOfWeek(dayOfWeek);
			entity.setTimeStart(timeStart);
			entity.setTimeEnd(timeEnd);
			repository.save(entity);
		}

	}

	// DELETE
	public void delete(int notId) {
		Optional<NotTeach> opt = repository.findByNotId(notId);
		NotTeach entity = new NotTeach();

<<<<<<< Updated upstream
		if (opt.isPresent()) {
=======
		if (!opt.isPresent()) {
>>>>>>> Stashed changes
			entity = opt.get();
			repository.delete(entity);
		}
	}

}
