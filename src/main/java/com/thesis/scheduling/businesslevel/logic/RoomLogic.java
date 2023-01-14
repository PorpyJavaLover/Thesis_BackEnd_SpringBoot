package com.thesis.scheduling.businesslevel.logic;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.mapper.RoomMapper;
import com.thesis.scheduling.modellevel.service.RoomService;

@Service
public class RoomLogic {
	
	private final RoomMapper classroomMapper;
	
	private final RoomService classroomService;

	public RoomLogic(RoomMapper classroomMapper, RoomService classroomService) {
		this.classroomMapper = classroomMapper;
		this.classroomService = classroomService;
	}
	
	//GET
	
	//SET
	
	//DELETE
	
}
