package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_Timetable_ShowAllStaff_Response_MM_Member {

	private int memberId;
	
	private String thFirstName;
	
	private String thLastName;
	
	private boolean selected = true;
}
