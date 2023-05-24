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
import com.thesis.scheduling.modellevel.model.M_NotTeach_CreateStaff_Request;
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
	@GetMapping("/teacher/show/all/{years}/{semester}")
	public ResponseEntity<Iterable<M_NotTeach_ShowAllTeacher_Response>> showAllTeacher(@PathVariable("years") String years, @PathVariable("semester") String semester ) throws BaseException {
		Iterable<M_NotTeach_ShowAllTeacher_Response> response = notteachlogic.showAllTeacher(years , semester);
		System.out.println("notteach show teacher");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/all/{years}/{semester}/{memberId}")
	public ResponseEntity<Iterable<M_NotTeach_ShowAllTeacher_Response>> showAllStaff(@PathVariable("years") String years, @PathVariable("semester") String semester ,@PathVariable("memberId") Integer memberId) throws BaseException {
		Iterable<M_NotTeach_ShowAllTeacher_Response> response = notteachlogic.showAllStaff(years , semester , memberId);
		System.out.println("notteach show teacher" + years + semester + memberId);
		return ResponseEntity.ok(response);
	}

	// SET
	@PostMapping("/teacher/create")
	public void createNotTeaching(@RequestBody M_NotTeach_CreateTeacher_Request request) {
		notteachlogic.createTeacher(request);
		System.out.println("notteach create");
	}

	@PostMapping("/staff/create")
	public void createNotTeaching(@RequestBody M_NotTeach_CreateStaff_Request request) {
		notteachlogic.createStaff(request);
		System.out.println("notteach create");
	}
	
	@PutMapping("/teacher/update/{notId}")
	public void editNotTeaching(@PathVariable("notId") int notId ,@RequestBody M_NotTeach_CreateTeacher_Request request) {
		notteachlogic.update(notId,request);
		System.out.println("notteach update");
	}

	@PutMapping("/staff/update/{notId}")
	public void editNotStaff(@PathVariable("notId") int notId ,@RequestBody M_NotTeach_CreateTeacher_Request request) {
		notteachlogic.update(notId,request);
		System.out.println("notteach update");
	}

	// DELETE
	@DeleteMapping("/teacher/delete/{notId}")
	public void deleteTeacher(@PathVariable("notId") int notId) throws BaseException {
		notteachlogic.delete(notId);
		System.out.println("delete create");
	}

	@DeleteMapping("/staff/delete/{notId}")
	public void deleteStaff(@PathVariable("notId") int notId) throws BaseException {
		notteachlogic.delete(notId);
		System.out.println("delete create");
	}



}
