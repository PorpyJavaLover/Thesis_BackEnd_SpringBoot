package com.thesis.scheduling.businesslevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.logic.MemberLogic;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Response;
import com.thesis.scheduling.modellevel.model.M_Member_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Response;

@RestController
@RequestMapping("/member")
public class MemberAPIController {

    private final MemberLogic memberLogic;

    public MemberAPIController(MemberLogic memberlogic) {
        this.memberLogic = memberlogic;
    }

    // GET
    @GetMapping("/staff/show/all")
    public ResponseEntity<Iterable<M_Member_ShowAllStaff_Response>> showAllStaff() throws BaseException {
        Iterable<M_Member_ShowAllStaff_Response> response = memberLogic.showAllStaff();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/anonymous/login")
    public ResponseEntity<M_Member_Login_Response> login(@RequestBody M_Member_Login_Request request)
            throws BaseException {
        M_Member_Login_Response response = memberLogic.login(request);
        return ResponseEntity.ok(response);
    }

    // SET
    @PostMapping("/anonymous/register")
    public ResponseEntity<M_Member_Register_Response> register(@RequestBody M_Member_Register_Request request)
            throws BaseException {
        System.out.println("register");
        M_Member_Register_Response response = memberLogic.register(request);
        return ResponseEntity.ok(response);
    }

    // DELETE

}
