package com.thesis.scheduling.modellevel.model;

import java.sql.Time;

import lombok.Data;

@Data
public class M_Timetable_ShowMemberTimeTeacher_Response {

	private Integer id;
	
	private Integer day_of_week;
	
	private Time start_time;
	
	private Time end_time;

}
