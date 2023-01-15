package com.thesis.scheduling.modellevel.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.repository.RoomRepository;

@Service
public class RoomService {

	private final RoomRepository repository;

	public RoomService(RoomRepository repository) {
		this.repository = repository;
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

		if (opt.isPresent()) {
			entity = opt.get();
			repository.delete(entity);
		}

	}
}
