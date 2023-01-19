package com.thesis.scheduling.modellevel.model;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.entity.Member;

import lombok.Data;

@Data
public class M_Plan_ShowAllStaff_Response {

	private String years;
	
	private String semester;
	
	private String course_id;
	
	private String course_code;
	
	private String course_title;
	
	private String group_id;
	
	private String group_name;
	
	private boolean selected_lect;
	
	private boolean selected_perf;

	private boolean disable_lect;

	private boolean disable_perf;
}
