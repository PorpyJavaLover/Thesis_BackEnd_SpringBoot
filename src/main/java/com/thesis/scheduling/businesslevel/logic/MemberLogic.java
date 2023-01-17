package com.thesis.scheduling.businesslevel.logic;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.thesis.scheduling.businesslevel.config.token.TokenService;
import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.exception.MemberException;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Title;
import com.thesis.scheduling.modellevel.mapper.MemberMapper;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Login_Response;
import com.thesis.scheduling.modellevel.model.M_Member_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Request;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Response;
import com.thesis.scheduling.modellevel.service.MemberService;
import com.thesis.scheduling.modellevel.service.TitleService;

@Service
public class MemberLogic {

	private final TitleService titleService;

	private final MemberService memberService;

	private final TokenService tokenService;

	private final MemberMapper memberMapper;

	public MemberLogic(TitleService titleservice, MemberService memberService, TokenService tokenService,
			MemberMapper memberMapper) {
		this.titleService = titleservice;
		this.memberService = memberService;
		this.tokenService = tokenService;
		this.memberMapper = memberMapper;
	}

	// GET
	public Iterable<M_Member_ShowAllStaff_Response> showAllStaff() {
		return memberMapper.toMShowAll(memberService.findAll());
	}
	
	
	public M_Member_Login_Response login(M_Member_Login_Request request) throws BaseException {

		// find user name
		Optional<Member> opt = memberService.findByUsername(request.getUsername());
<<<<<<< Updated upstream
		if (!opt.isPresent()) {
=======
		if (opt.isPresent()) {
>>>>>>> Stashed changes
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

		Optional<Title> title = titleService.findByTitleId(request.getTitle_id());
		Member member = memberService.create(title.get(), request.getTh_first_name(), request.getTh_last_name(),
				request.getUsername(), request.getPassword());
		return memberMapper.toMRegisterResponse(member);
	}
	

	// DELETE

}
