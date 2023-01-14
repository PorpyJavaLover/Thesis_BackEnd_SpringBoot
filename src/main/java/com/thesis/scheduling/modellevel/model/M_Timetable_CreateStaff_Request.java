package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_Timetable_CreateStaff_Request {
	
	private String years;

	private String semester;

	private Long courseId;
	
	private Integer courseType;
	
	private Long groupId;
	
	private int memberId;
	
}
