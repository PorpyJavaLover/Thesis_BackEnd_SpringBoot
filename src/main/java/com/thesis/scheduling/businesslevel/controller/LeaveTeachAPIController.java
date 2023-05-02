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
import com.thesis.scheduling.businesslevel.logic.LeaveTeachLogic;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_CreateStaff_Request;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_LeaveTeach_ShowAllTeacher_Response;

@RestController
@RequestMapping("/leaveteach")
public class LeaveTeachAPIController {

	private final LeaveTeachLogic leaveTeachLogic;

	public LeaveTeachAPIController(LeaveTeachLogic leaveTeachLogic) {
		this.leaveTeachLogic = leaveTeachLogic;
	}

	// GET
	@GetMapping("/teacher/show/all/{year}/{semester}")
	public ResponseEntity<Iterable<M_LeaveTeach_ShowAllTeacher_Response>> showAllTeacher(@PathVariable("year") String year, @PathVariable("semester") String semester) throws BaseException {
		Iterable<M_LeaveTeach_ShowAllTeacher_Response> response = leaveTeachLogic.showAllTeacher(year, semester);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/all/{year}/{semester}/{memberId}")
	public ResponseEntity<Iterable<M_LeaveTeach_ShowAllTeacher_Response>> showAllStaff(@PathVariable("year") String year, @PathVariable("semester") String semester, @PathVariable("memberId") int memberId) throws BaseException {
		Iterable<M_LeaveTeach_ShowAllTeacher_Response> response = leaveTeachLogic.showAllStaff(year, semester, memberId);
		return ResponseEntity.ok(response);
	}

	// SET
	@PostMapping("/teacher/create")
	public void createTeacher(@RequestBody M_LeaveTeach_CreateTeacher_Request request) throws BaseException {
		leaveTeachLogic.createTeacher(request);
	}

	@PostMapping("/staff/create")
	public void createTeacher(@RequestBody M_LeaveTeach_CreateStaff_Request request) throws BaseException {
		leaveTeachLogic.createStaff(request);
	}
	
	@PutMapping("/teacher/update/{leaveTeachId}")
	public void editLeaveTeaching(@PathVariable("leaveTeachId") int leaveTeachId ,@RequestBody M_LeaveTeach_CreateTeacher_Request request) {
		System.out.println(request);
		leaveTeachLogic.update(leaveTeachId,request);
	}

	// DELETE
	@DeleteMapping("/teacher/delete/{leaveTeachId}")
	public void deleteTeacher(@PathVariable("leaveTeachId") int leaveTeachId) throws BaseException {
		System.out.println(leaveTeachId);
		leaveTeachLogic.delete(leaveTeachId);
	}
	


}
