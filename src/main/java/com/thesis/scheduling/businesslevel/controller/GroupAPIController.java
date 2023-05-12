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
import com.thesis.scheduling.businesslevel.logic.GroupLogic;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;

@RestController
@RequestMapping("/group")
public class GroupAPIController {

    private final GroupLogic groupLogic;

    public GroupAPIController(GroupLogic groupLogic) {
        this.groupLogic = groupLogic;
    }

    	// GET
	@GetMapping("/staff/show/option")
	public ResponseEntity<Iterable<M_For_Selection_Response>> showGroupOption() throws BaseException {
		Iterable<M_For_Selection_Response> response = groupLogic.showGroupOption();
		return ResponseEntity.ok(response);
	}

    
}
