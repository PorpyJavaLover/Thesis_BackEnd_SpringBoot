package com.thesis.scheduling.businesslevel.logic;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.businesslevel.config.token.TokenService;
import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.exception.MemberException;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.entity.Title;
import com.thesis.scheduling.modellevel.mapper.MemberMapper;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Response;
import com.thesis.scheduling.modellevel.model.M_Member_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Member_UpdateStaff_Request;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.OrganizationService;
import com.thesis.scheduling.modellevel.service.TitleService;

@Service
public class MemberLogic {

	private final TitleService titleService;

	private final MemberService memberService;

	private final TokenService tokenService;

	private final MemberMapper memberMapper;

	private final OrganizationService organizationService;

	public MemberLogic(TitleService titleService, MemberService memberService, TokenService tokenService,
			MemberMapper memberMapper, OrganizationService organizationService) {
		this.titleService = titleService;
		this.memberService = memberService;
		this.tokenService = tokenService;
		this.memberMapper = memberMapper;
		this.organizationService = organizationService;
	}

	private int getCurrentUserId() {
		Optional<String> opt = SecurityUtil.getCurrentUserId();
		return Integer.parseInt(opt.get());
	}

	// GET

	public Iterable<M_Member_ShowAllStaff_Response> showMemberStaff() {
		Organization sourceA = memberService.findByMemberId(getCurrentUserId()).get().getOrganizationId();
		Collection<Member> sourceB = memberService.findAllBySOrganizationId(sourceA);
		return memberMapper.toMShowMember(sourceB);
	}

	public Iterable<M_For_Selection_Response> showMemberStaffForOption() {
		Organization sourceA = memberService.findByMemberId(getCurrentUserId()).get().getOrganizationId();
		Collection<Member> sourceB = memberService.findAllBySOrganizationId(sourceA);
		return memberMapper.toMShowMemberForOption(sourceB);
	}

	public M_Member_Login_Response login(M_Member_Login_Request request) throws BaseException {

		// find user name
		Optional<Member> opt = memberService.findByUsername(request.getUsername());
		if (!opt.isPresent()) {
			throw MemberException.loginFailUsernameNotFound();
		}

		Member member = opt.get();

		// verify password
		if (!memberService.matchPassword(request.getPassword(), member.getPassword())) {
			throw MemberException.loginFailPasswordIncorrect();
		}

		// create token
		M_Member_Login_Response response = new M_Member_Login_Response();
		response.setToken(tokenService.tokenize(member));
		return response;
	}

	// SET
	public M_Member_Register_Response register(M_Member_Register_Request request) throws BaseException {


		Optional<Title> title = titleService.findByTitleId(request.getTitleNameSelected());
		Optional<Organization> organiz = organizationService.findByCode(request.getOrganizSelected());
		Member member = memberService.create(title.get(), organiz.get(), request.getFirstNameTH(),
				request.getLastNameTH(), request.getFirstNameEN(), request.getLastNameEN()
				, request.getUsernameRe(), request.getPasswordRe() , request.getRoleSelected() );
		return memberMapper.toMRegisterResponse(member);
	}

	public void update( int memberId , M_Member_UpdateStaff_Request request) throws BaseException {

		Optional<Title> title = titleService.findByTitleId(request.getTitleNameSelected());
		 memberService.update(memberService.findByMemberId(memberId).get() , title.get(), request.getFirstNameTH(),
				request.getLastNameTH(), request.getFirstNameEN(), request.getLastNameEN()
				, request.getUsernameRe(), request.getPasswordRe(), request.getRoleSelected() , request.isActiveStatusSelected());

	}

	// DELETE

	public void delete( int memberId) throws BaseException {

		 memberService.delete(memberService.findByMemberId(memberId).get());
				
	}

}
