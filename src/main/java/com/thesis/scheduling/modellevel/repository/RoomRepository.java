package com.thesis.scheduling.modellevel.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.CpeRoom;
import com.thesis.scheduling.modellevel.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, String> {
	
	Collection<Room> findAllByRoomNoStartingWith(String name);
	
	Optional<Room> findAllByRoomId(Integer roomId);

}
