package com.thesis.scheduling.modellevel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.thesis.scheduling.modellevel.entity.CpeRoom;

public interface CpeRoomRepository extends CrudRepository<CpeRoom, String> {
	
	List<CpeRoom> findAllByRoomNoStartingWith(String name);
	
	Optional<CpeRoom> findAllByRoomId(Integer roomId);
}
