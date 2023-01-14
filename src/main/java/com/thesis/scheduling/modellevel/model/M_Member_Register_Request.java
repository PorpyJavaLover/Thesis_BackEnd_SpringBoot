package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_Member_Register_Request {
	
	private Long title_id;
    
	private String th_first_name;

	private String th_last_name;

	private String username;

	private String password;

}
