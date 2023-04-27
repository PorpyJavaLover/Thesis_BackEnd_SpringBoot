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
import com.thesis.scheduling.businesslevel.logic.ReplaceTeachLogic;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_UpdateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_SelectOption_Response;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_PDFBodyTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_PDFHeadTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_ShowAllTeacher_Response;

@RestController
@RequestMapping("/replaceteach")
public class ReplaceTeachAPIController {

	private final ReplaceTeachLogic replaceTeachLogic;

	public ReplaceTeachAPIController(ReplaceTeachLogic replaceteaclogic) {
		this.replaceTeachLogic = replaceteaclogic;
	}

	// GET
	@GetMapping("/teacher/show/all/{year}/{semester}")
	public ResponseEntity<Iterable<M_ReplaceTeach_ShowAllTeacher_Response>> showAllTeacher(@PathVariable("year") String year, @PathVariable("semester") String semester) throws BaseException {
		Iterable<M_ReplaceTeach_ShowAllTeacher_Response> response = replaceTeachLogic.showAllTeacher(year, semester);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/staff/show/all/{year}/{semester}/{memberId}")
	public ResponseEntity<Iterable<M_ReplaceTeach_ShowAllTeacher_Response>> showAllStaff(@PathVariable("year") String year, @PathVariable("semester") String semester, @PathVariable("memberId") int memberId ) throws BaseException {
		Iterable<M_ReplaceTeach_ShowAllTeacher_Response> response = replaceTeachLogic.showAllStaff(year, semester, memberId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/teacher/member/replace/option/{replaceTeachId}/{organize}")
	public ResponseEntity<Iterable<M_SelectOption_Response>> showMemberReplaceOption( @PathVariable("replaceTeachId") int replaceTeachId ,@PathVariable("organize") String organize) throws BaseException {
		Iterable<M_SelectOption_Response> response = replaceTeachLogic.showMemberReplaceOption(replaceTeachId , organize);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/teacher/pdf/head/{replaceTeachId}")
	public ResponseEntity<M_ReplaceTeach_PDFHeadTeacher_Response> showPDFHeadTeacher( @PathVariable("replaceTeachId") int replaceTeachId) throws BaseException {
		M_ReplaceTeach_PDFHeadTeacher_Response response = replaceTeachLogic.showPDFHeadTeacher(replaceTeachId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/teacher/pdf/body/{leaveTeachId}/{replaceTeachId}")
	public ResponseEntity<Iterable<M_ReplaceTeach_PDFBodyTeacher_Response>> showPDFBodyTeacher( @PathVariable("leaveTeachId") int leaveTeachId , @PathVariable("replaceTeachId") int replaceTeachId) throws BaseException {
		Iterable<M_ReplaceTeach_PDFBodyTeacher_Response> response = replaceTeachLogic.showPDFBodyTeacher(leaveTeachId,replaceTeachId);
		return ResponseEntity.ok(response);
	}

	// SET
	@PostMapping("/teacher/create")
	public void createTeacher(@RequestBody M_ReplaceTeach_UpdateTeacher_Request request) throws BaseException {
		
	}

	@PutMapping("/teacher/update/{replaceTeachId}")
	public void updateTeacher( @PathVariable("replaceTeachId") int replaceTeachId , @RequestBody M_ReplaceTeach_UpdateTeacher_Request request) throws BaseException {
		replaceTeachLogic.updateTeacher(replaceTeachId,request);
	}

	// DELETE
	@DeleteMapping("/teacher/delete/{replaceTeachId}")
	public void deleteTeacher(@PathVariable("replaceTeachId") int replaceTeachId) throws BaseException {
		replaceTeachLogic.deleteTeacher(replaceTeachId);
	}
}
