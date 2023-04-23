package com.thesis.scheduling.modellevel.model;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;

import lombok.Data;

@Data
public class M_Timetable_ShowAllStaff_Response {
	
	private Integer id;
	
	private String years;
	
	private String semester;
	
	private String course_id;
	
	private String course_name;

	private String course_code;
	
	private Integer course_type;
	
	private String course_type_name;
	
	private String group_id;
	
	private String group_name;
	
	private Integer member_Id;
	
	private String member_name;
	
	private String day_of_week;

	private String day_of_week_name;
	
	private String start_time;
	
	private String end_time;
	
	private String room_id;
	
	private String room_name;
	
	private boolean time_locker;
	
	private boolean room_locker;

	private Collection<HashMap<String, String>> member;
}
