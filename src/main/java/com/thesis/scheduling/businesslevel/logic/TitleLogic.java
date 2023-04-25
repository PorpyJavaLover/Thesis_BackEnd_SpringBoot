package com.thesis.scheduling.businesslevel.logic;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.config.SecurityUtil;
import com.thesis.scheduling.modellevel.mapper.TitleMapper;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Title_CreateTeacher_Request;
import com.thesis.scheduling.modellevel.model.M_Tittle_ShowAllPublic_Response;
import com.thesis.scheduling.modellevel.service.TitleService;

@Service
public class TitleLogic {

	private final TitleMapper titleMapper;

	private final TitleService titleService;

	public TitleLogic(TitleService titleService, TitleMapper titleMapper) {
		this.titleMapper = titleMapper;
		this.titleService = titleService;
	}

	private String getCurrentUserRole() {
		Optional<String> holderRole = SecurityUtil.getCurrentUserRole();
		return holderRole.get().toString();
	}

	// GET
	public Iterable<M_For_Selection_Response> showTitleOption() {
		return titleMapper.toMShowTitleOption(titleService.showAll());
	}

	// SET
	public void createStaff(M_Title_CreateTeacher_Request request) {
		if (getCurrentUserRole().equals("ROLE_STAFF") || getCurrentUserRole().equals("ROLE_ADMIN")) {
			titleService.createStaff(request.getTitleName(), request.getTitleShort());
		}
	}

	// DELETE
	public void delete(Long titleId) {
		if (getCurrentUserRole().equals("ROLE_STAFF") || getCurrentUserRole().equals("ROLE_ADMIN")) {
			titleService.delete(titleId);
		}
	}
}
