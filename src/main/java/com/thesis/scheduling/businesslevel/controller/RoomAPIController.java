package com.thesis.scheduling.businesslevel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.scheduling.businesslevel.logic.RoomLogic;

@RestController
@RequestMapping("/classroom")
public class RoomAPIController {
	
	private final RoomLogic classroomLogic;

	public RoomAPIController(RoomLogic classroomLogic) {
		this.classroomLogic = classroomLogic;
	}
	
	// SET

	// GET

	// DELETE

}
