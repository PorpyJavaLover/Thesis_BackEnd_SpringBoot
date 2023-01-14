package com.thesis.scheduling.businesslevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.logic.NotTeachLogic;
import com.thesis.scheduling.modellevel.model.M_NotTeach_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_NotTeach_ShowAllTeacher_Response;

@RestController
@RequestMapping("/notteach")
public class NotTeachAPIController {

	private final NotTeachLogic notteachlogic;

	public NotTeachAPIController(NotTeachLogic notteachlogic) {
		this.notteachlogic = notteachlogic;
	}

	// GET
	@GetMapping("/teacher/show/all")
	public ResponseEntity<Iterable<M_NotTeach_ShowAllTeacher_Response>> showAllTeacher() throws BaseException {
		Iterable<M_NotTeach_ShowAllTeacher_Response> response = notteachlogic.showAllTeacher();
		System.out.println("notteach show teacher");
		return ResponseEntity.ok(response);
	}

	// SET
	@PostMapping("/teacher/create")
	public void createNotTeaching(@RequestBody M_NotTeach_CreateTeacher_Request request) {
		notteachlogic.create(request);
		System.out.println("notteach create");
	}
	
	@PutMapping("/teacher/update/{notId}")
	public void editNotTeaching(@PathVariable("notId") int notId ,@RequestBody M_NotTeach_CreateTeacher_Request request) {
		notteachlogic.update(notId,request);
		System.out.println("notteach update");
	}

	// DELETE
	@DeleteMapping("/teacher/delete/{notId}")
	public void deleteTeacher(@PathVariable("notId") int notId) throws BaseException {
		notteachlogic.delete(notId);
		System.out.println("delete create");
	}
}
