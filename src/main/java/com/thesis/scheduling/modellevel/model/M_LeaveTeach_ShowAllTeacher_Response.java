package com.thesis.scheduling.modellevel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class M_LeaveTeach_ShowAllTeacher_Response {
	
	@JsonProperty("id")
	private int leaveTeachId ;

	private String years_name;

	private String years_value;
    
	private String semester;
    
	private java.sql.Date dateStart;
    
	private java.sql.Date dateEnd;

	private java.sql.Date dateStart_value;
    
	private java.sql.Date dateEnd_value;
	
	private String note;
}
