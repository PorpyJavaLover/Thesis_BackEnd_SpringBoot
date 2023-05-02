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
import com.thesis.scheduling.businesslevel.logic.MemberLogic;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Response;
import com.thesis.scheduling.modellevel.model.M_Member_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Member_UpdateStaff_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Response;
import com.thesis.scheduling.modellevel.model.M_Member_RolePlay_Request;

@RestController
@RequestMapping("/member")
public class MemberAPIController {

    private final MemberLogic memberLogic;

    public MemberAPIController(MemberLogic memberlogic) {
        this.memberLogic = memberlogic;
    }

    // GET
    @GetMapping("/staff/show/all")
    public ResponseEntity<Iterable<M_Member_ShowAllStaff_Response>> showMemberStaff() throws BaseException {
        Iterable<M_Member_ShowAllStaff_Response> response = memberLogic.showMemberStaff();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/staff/show/option")
    public ResponseEntity<Iterable<M_For_Selection_Response>> showMemberStaffForOption() throws BaseException {
        Iterable<M_For_Selection_Response> response = memberLogic.showMemberStaffForOption();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/show/option/{organiz}")
    public ResponseEntity<Iterable<M_For_Selection_Response>> showMemberStaffForOption( @PathVariable("organiz") String organiz) throws BaseException {
        Iterable<M_For_Selection_Response> response = memberLogic.showMemberAdminForOption(organiz);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/show/all/{organiz}")
    public ResponseEntity<Iterable<M_Member_ShowAllStaff_Response>> showMemberAdmin(@PathVariable("organiz") String organiz) throws BaseException {
        Iterable<M_Member_ShowAllStaff_Response> response = memberLogic.showMemberAdmin(organiz);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/anonymous/login")
    public ResponseEntity<M_Member_Login_Response> login(@RequestBody M_Member_Login_Request request)
            throws BaseException {
        M_Member_Login_Response response = memberLogic.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/role/play")
    public ResponseEntity<M_Member_Login_Response> rolePlay(@RequestBody M_Member_RolePlay_Request request)
            throws BaseException {
        M_Member_Login_Response response = memberLogic.rolePlay(request);
        return ResponseEntity.ok(response);
    }

    // SET
    @PostMapping("/anonymous/register")
    public ResponseEntity<M_Member_Register_Response> register(@RequestBody M_Member_Register_Request request)
            throws BaseException {
        M_Member_Register_Response response = memberLogic.register(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/staff/update/{memberId}")
    public void update(@PathVariable("memberId") int memberId, @RequestBody M_Member_UpdateStaff_Request request)
            throws BaseException {
        memberLogic.update(memberId , request);
    }

    // DELETE
    @DeleteMapping("/staff/delete/{memberId}")
    public void delete(@PathVariable("memberId") int memberId)
            throws BaseException {
        memberLogic.delete(memberId);
    }
}
