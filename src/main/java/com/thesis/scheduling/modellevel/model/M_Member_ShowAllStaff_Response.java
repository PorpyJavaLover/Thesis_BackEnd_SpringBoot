package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_Member_ShowAllStaff_Response {
	
	private Integer memberId;

	private Integer titleId;

	private String titleName;
    
	private String firstNameTH;

	private String lastNameTH;

	private String firstNameEN;

	private String lastNameEN;

	private String username;

	private String password;

	private Integer role;

	private boolean activeStatus;
	
}
