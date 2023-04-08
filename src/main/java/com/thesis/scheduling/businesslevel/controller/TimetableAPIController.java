package com.thesis.scheduling.businesslevel.controller;

import java.sql.Time;
import java.text.ParseException;

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
import com.thesis.scheduling.businesslevel.logic.TimetableLogic;
import com.thesis.scheduling.modellevel.model.M_Timetable_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_CreateStaff_Request;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowTimeRemain_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_UpdateLockerStaff_Request;
import com.thesis.scheduling.modellevel.model.M_Timetable_UpdateStaff_Request;

@RestController
@RequestMapping("/timetable")
public class TimetableAPIController {

	private final TimetableLogic timetableLogic;

	public TimetableAPIController(TimetableLogic timetablelogic) {
		this.timetableLogic = timetablelogic;
	}

	// GET
	@GetMapping("/teacher/show/all/ForPlan")
	public ResponseEntity<Iterable<M_Timetable_ShowAllTeacher_Response>> showAllTeacherForPlan() throws BaseException {
		Iterable<M_Timetable_ShowAllTeacher_Response> response = timetableLogic.showAllTeacherForPlan();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/teacher/show/all")
	public ResponseEntity<Iterable<M_Timetable_ShowAllStaff_Response>> showAllTeacher() throws BaseException {
		Iterable<M_Timetable_ShowAllStaff_Response> response = timetableLogic.showAllTeacher();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/all")
	public ResponseEntity<Iterable<M_Timetable_ShowAllStaff_Response>> showAllStaff() throws BaseException {
		Iterable<M_Timetable_ShowAllStaff_Response> response = timetableLogic.showAllStaff();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/all/{memberId}")
	public ResponseEntity<Iterable<M_Timetable_ShowAllTeacher_Response>> showAllStaffByMemberForPlan(
			@PathVariable("memberId") Integer memberId) throws BaseException {
		Iterable<M_Timetable_ShowAllTeacher_Response> response = timetableLogic.showAllStaffByMemberIdForPlan(memberId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/start/time/option/{yId}/{sId}/{cId}/{cType}/{gId}/{dayOfWeek}/{endTime}")
	public ResponseEntity<Iterable<M_Timetable_ShowTimeRemain_Response>> showStartTimeForOption(
			@PathVariable("yId") String yId,
			@PathVariable("sId") String sId, @PathVariable("cId") Long cId, @PathVariable("cType") Integer cType,
			@PathVariable("gId") Long gId,
			@PathVariable("dayOfWeek") Integer dayOfWeek, @PathVariable("endTime") String endTime)
			throws BaseException {
		Iterable<M_Timetable_ShowTimeRemain_Response> response = timetableLogic.showStartTimeOptionStaff(false, yId,
				sId, cId, cType, gId, dayOfWeek);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/end/time/option/{yId}/{sId}/{cId}/{cType}/{gId}/{dayOfWeek}/{startTime}")
	public ResponseEntity<Iterable<M_Timetable_ShowTimeRemain_Response>> getEndTimeForOption(
			@PathVariable("yId") String yId,
			@PathVariable("sId") String sId, @PathVariable("cId") Long cId, @PathVariable("cType") Integer cType,
			@PathVariable("gId") Long gId,
			@PathVariable("dayOfWeek") Integer dayOfWeek, @PathVariable("startTime") String startTime)
			throws BaseException {
		Iterable<M_Timetable_ShowTimeRemain_Response> response = timetableLogic.showEndTimeOptionStaff(yId, sId, cId,
				cType, gId,
				dayOfWeek);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/start/time/{yId}/{sId}/{cId}/{cType}/{gId}/{dayOfWeek}/{endTime}")
	public ResponseEntity<M_Timetable_ShowTimeRemain_Response> showStartTimeForSelection(
			@PathVariable("yId") String yId,
			@PathVariable("sId") String sId, @PathVariable("cId") Long cId, @PathVariable("cType") Integer cType,
			@PathVariable("gId") Long gId,
			@PathVariable("dayOfWeek") Integer dayOfWeek, @PathVariable("endTime") String endTime)
			throws BaseException {
		M_Timetable_ShowTimeRemain_Response response = timetableLogic.showStartTimeStaff(yId, sId, cId, cType, gId,
				dayOfWeek, endTime);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/end/time/{yId}/{sId}/{cId}/{cType}/{gId}/{dayOfWeek}/{startTime}")
	public ResponseEntity<M_Timetable_ShowTimeRemain_Response> getEndTimeForSelection(@PathVariable("yId") String yId,
			@PathVariable("sId") String sId, @PathVariable("cId") Long cId, @PathVariable("cType") Integer cType,
			@PathVariable("gId") Long gId,
			@PathVariable("dayOfWeek") Integer dayOfWeek, @PathVariable("startTime") String startTime)
			throws BaseException {
		M_Timetable_ShowTimeRemain_Response response = timetableLogic.showEndTimeStaff(yId, sId, cId, cType, gId,
				dayOfWeek, startTime);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/room/{yId}/{sId}/{cId}/{cType}/{gId}/{dayOfWeek}/{startTime}/{endTime}")
	public ResponseEntity<Iterable<M_For_Selection_Response>> getRoomForSelection(@PathVariable("yId") String yId,
			@PathVariable("sId") String sId, @PathVariable("cId") Long cId, @PathVariable("cType") Integer cType,
			@PathVariable("gId") Long gId,
			@PathVariable("dayOfWeek") Integer dayOfWeek, @PathVariable("startTime") Time startTime,
			@PathVariable("endTime") Time endTime) throws BaseException {
		Iterable<M_For_Selection_Response> response = timetableLogic.showRoomStaff(false, yId, sId, cId, cType, gId,
				dayOfWeek, startTime, endTime);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/auto_pilot")
	public void autoPilot() throws BaseException, ParseException {
		timetableLogic.autoPilot();
	}

	// SET
	@PostMapping("/teacher/create")
	public void createTeacher(@RequestBody M_Timetable_CreateTeacher_Request request)
			throws BaseException {
		timetableLogic.createTeacher(request);
	}

	@PostMapping("/staff/create")
	public void createStaff(@RequestBody M_Timetable_CreateStaff_Request request)
			throws BaseException {
		timetableLogic.createStaff(request);
	}

	@PutMapping("/staff/update/{yId}/{sId}/{cId}/{cType}/{gId}")
	public void updateStaff(@PathVariable("yId") String yId, @PathVariable("sId") String sId,
			@PathVariable("cId") Long cId, @PathVariable("cType") Integer cType, @PathVariable("gId") Long gId,
			@RequestBody M_Timetable_UpdateStaff_Request request) {
		timetableLogic.updateStaff(yId, sId, cId, cType, gId, request);
	}

	@PutMapping("/staff/update/locker/{yId}/{sId}/{cId}/{cType}/{gId}")
	public void updateLockerStaff(@PathVariable("yId") String yId, @PathVariable("sId") String sId,
			@PathVariable("cId") Long cId, @PathVariable("cType") Integer cType, @PathVariable("gId") Long gId,
			@RequestBody M_Timetable_UpdateLockerStaff_Request request) {
		timetableLogic.updateLockerStaff(yId, sId, cId, cType, gId, request);
	}

	// DELETE
	@DeleteMapping("/teacher/delete/{timetableId}")
	public void delete(@PathVariable("timetableId") int timetableId) throws BaseException {
		timetableLogic.delete(timetableId);
	}

	@DeleteMapping("/teacher/delete/forPlan/{yId}/{sId}/{cId}/{cType}/{gId}")
	public void deleteTeacherForPlan(@PathVariable("yId") String yId, @PathVariable("sId") String sId,
			@PathVariable("cId") Long cId, @PathVariable("cType") Integer cType, @PathVariable("gId") Long gId)
			throws BaseException {
		timetableLogic.deleteForPlanTeacher(yId, sId, cId, cType, gId);
	}

	@DeleteMapping("/staff/delete/forPlan/{yId}/{sId}/{cId}/{cType}/{gId}/{memberId}")
	public void deleteStaffForPlan(@PathVariable("yId") String yId, @PathVariable("sId") String sId,
			@PathVariable("cId") Long cId, @PathVariable("cType") Integer cType, @PathVariable("gId") Long gId,
			@PathVariable("memberId") Integer memberId)
			throws BaseException {
		timetableLogic.deleteForPlanStaff(yId, sId, cId, cType, gId, memberId);
	}

	@DeleteMapping("/staff/clean/{yId}/{sId}/{cId}/{cType}/{gId}/{dayOfWeek}/{startTime}/{endTime}/{room}/{timeLocker}/{roomLocker}")
	public void clean(@PathVariable("yId") String yId, @PathVariable("sId") String sId,
			@PathVariable("cId") Long cId, @PathVariable("cType") Integer cType, @PathVariable("gId") Long gId,
			@PathVariable("dayOfWeek") Integer dayOfWeek, @PathVariable("startTime") Time startTime,
			@PathVariable("endTime") Time endTime, @PathVariable("room") Integer roomId,
			@PathVariable("timeLocker") boolean timeLocker, @PathVariable("roomLocker") boolean roomLocker)
			throws BaseException {
		timetableLogic.clean(yId, sId, cId, cType, gId, dayOfWeek, startTime, endTime, roomId, timeLocker, roomLocker);
	}

	@DeleteMapping("/staff/clean/all")
	public void cleanAll() throws BaseException {
		timetableLogic.cleanAll();
	}

}
