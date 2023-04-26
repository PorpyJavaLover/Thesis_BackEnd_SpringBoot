package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_Member_UpdateStaff_Request {

    private Long titleNameSelected;

	private String firstNameTH;

	private String lastNameTH;

	private String firstNameEN;

	private String lastNameEN;

	private String usernameRe;

	private String passwordRe;

    private Integer roleSelected;
	
    private boolean activeStatusSelected;

}
