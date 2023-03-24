package com.thesis.scheduling.modellevel.model;

import java.util.Date;

import lombok.Data;

@Data
public class M_LeaveTeach_CreateTeacher_Request {
	
	private String year;

	private String semester;

	private Date  dateStart;

	private Date dateEnd;

	private String note;
	
}
