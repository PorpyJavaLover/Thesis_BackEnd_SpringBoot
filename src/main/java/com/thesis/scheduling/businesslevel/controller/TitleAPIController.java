package com.thesis.scheduling.businesslevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.logic.TitleLogic;
import com.thesis.scheduling.modellevel.model.M_Title_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_Tittle_ShowAllPublic_Response;

@RestController
@RequestMapping("/title")
public class TitleAPIController {
	
	private final TitleLogic titleLogic;

	public TitleAPIController(TitleLogic titleLogic) {
		this.titleLogic = titleLogic;
	}
	
	//GET
	@GetMapping("/public/show/all")
    public ResponseEntity<Iterable<M_Tittle_ShowAllPublic_Response> > showAllBublic() throws BaseException {
    	Iterable<M_Tittle_ShowAllPublic_Response>  response = titleLogic.showAllPublic();
        return ResponseEntity.ok(response);
    }
	
	//SET
	@PostMapping("/staff/create")
	public void createStaff(@RequestBody M_Title_CreateTeacher_Request request) {
		titleLogic.createStaff(request);
	}
	
	//DELETE
	@DeleteMapping("/teacher/delete/{titleId}")
	public void delete(@PathVariable("titleId") Long titleId) throws BaseException {
		titleLogic.delete(titleId);
	}
	

}
