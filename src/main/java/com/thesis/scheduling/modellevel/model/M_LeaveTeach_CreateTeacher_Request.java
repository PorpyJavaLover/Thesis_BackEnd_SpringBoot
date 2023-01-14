package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_LeaveTeach_CreateTeacher_Request {
	
	private String year;

	private String semester;

	private java.sql.Date dateStart;

	private java.sql.Date dateEnd;

	private String note;
	
}
