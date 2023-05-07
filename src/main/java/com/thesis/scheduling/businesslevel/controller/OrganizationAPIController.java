package com.thesis.scheduling.businesslevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.logic.OrganizationLogic;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Organiz_Response;

@RestController
@RequestMapping("/organization")
public class OrganizationAPIController {

	private final OrganizationLogic organizationLogic;

	public OrganizationAPIController(OrganizationLogic organizationLogic) {
		this.organizationLogic = organizationLogic;
	}
	
	// GET
	@GetMapping("/teacher/show/all")
	public  ResponseEntity<Iterable<M_For_Selection_Response>> showAllTeacher() throws BaseException  {
		Iterable<M_For_Selection_Response> response = organizationLogic.showAllTeacher();
		return  ResponseEntity.ok(response);
	}

	@GetMapping("/public/show/option/faculty")
	public  ResponseEntity<Iterable<M_For_Selection_Response>> showFacultyOption() throws BaseException  {
		Iterable<M_For_Selection_Response> response = organizationLogic.showFacultyOption();
		return  ResponseEntity.ok(response);
	}
	
	@GetMapping("/public/show/option/organiz/{parent}")
	public  ResponseEntity<Iterable<M_For_Selection_Response>> showOrganizOption(@PathVariable("parent") String parent) throws BaseException  {
		Iterable<M_For_Selection_Response> response = organizationLogic.showOrganizOption(parent);
		return  ResponseEntity.ok(response);
	}

	@GetMapping("/teacher/show/member/organiz")
    public ResponseEntity<M_Member_Organiz_Response> showMemberTeacherOrganiz() throws BaseException {
        M_Member_Organiz_Response response = organizationLogic.showMemberTeacherOrganiz();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/staff/show/member/organiz/{memberId}")
    public ResponseEntity<M_Member_Organiz_Response> showMemberStaffOrganiz(@PathVariable("memberId") int memberId) throws BaseException {
        M_Member_Organiz_Response response = organizationLogic.showMemberStaffOrganiz(memberId);
        return ResponseEntity.ok(response);
    }
	
	// SET
	
	
	// DELETE
	
	
}
