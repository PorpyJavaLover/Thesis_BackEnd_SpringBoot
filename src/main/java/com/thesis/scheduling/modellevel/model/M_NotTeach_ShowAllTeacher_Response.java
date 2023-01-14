package com.thesis.scheduling.modellevel.model;

import java.sql.Time;

import com.thesis.scheduling.modellevel.entity.Member;

import lombok.Data;

@Data
public class M_NotTeach_ShowAllTeacher_Response {

	private int notId;
	
    private Member memberId;

	private int dayOfWeek;
	
	private Time timeStart;

	private Time timeEnd;
	
	
}
