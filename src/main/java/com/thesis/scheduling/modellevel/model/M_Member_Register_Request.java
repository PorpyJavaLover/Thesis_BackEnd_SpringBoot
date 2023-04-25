package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_Member_Register_Request {
	
	private Long titleNameSelected;

	private String organizSelected;

	private String firstNameTH;

	private String lastNameTH;

	private String firstNameEN;

	private String lastNameEN;

	private String usernameRe;

	private String passwordRe;
	
	private String confirmPasswordRe;
}
