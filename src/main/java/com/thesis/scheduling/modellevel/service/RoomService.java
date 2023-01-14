package com.thesis.scheduling.modellevel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.CpeRoom;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.repository.CpeRoomRepository;
import com.thesis.scheduling.modellevel.repository.RoomRepository;

@Service
public class RoomService {

	private final RoomRepository repository;
	
	private final CpeRoomRepository cpeRepository;

	public RoomService(RoomRepository repository, CpeRoomRepository cpeRepository) {
		this.repository = repository;
		this.cpeRepository = cpeRepository;
	}

	// SET
	
	public Iterable<Room> findAll() {

		Collection<Room> resultA =  repository.findAllByRoomNoStartingWith("18");
		Collection<Room> resultB =  repository.findAllByRoomNoStartingWith("36");
		resultA.addAll(resultB);
		return resultA;
	}
	
	public Optional<Room> findAllByRoomId(Integer roomId) {
		return repository.findAllByRoomId(roomId);
	}

	// GET

	// DELETE
	
	public void delete(Integer roomId) {

		Room entity = new Room();

		Optional<Room> opt = repository.findAllByRoomId(roomId);

		if (!opt.isEmpty()) {
			entity = opt.get();
			repository.delete(entity);
		}

	}
}
