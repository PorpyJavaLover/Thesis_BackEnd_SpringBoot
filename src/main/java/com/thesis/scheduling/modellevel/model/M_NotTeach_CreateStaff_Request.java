package com.thesis.scheduling.modellevel.model;

import java.sql.Time;

import lombok.Data;

@Data
public class M_NotTeach_CreateStaff_Request {

	private int dayOfWeek;

	private Time timeStart;
	
	private Time timeEnd;

	private int memberId;
	
}
