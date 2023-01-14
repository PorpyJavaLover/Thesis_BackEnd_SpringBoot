package com.thesis.scheduling.modellevel.model;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;

import lombok.Data;

@Data
public class M_Timetable_ShowAllTeacher_Response {
	
	private String years;
	
	private String semester;
	
	private String course_id;
	
	private Integer course_type;
	
	private String group_id;
	
	private String member_id;
}
