package com.thesis.scheduling.modellevel.model;

import java.sql.Time;

import lombok.Data;

@Data
public class M_NotTeach_CreateTeacher_Request {

	private String years;

	private String semester;

	private int dayOfWeek;

	private Time timeStart;
	
	private Time timeEnd;
	
}
