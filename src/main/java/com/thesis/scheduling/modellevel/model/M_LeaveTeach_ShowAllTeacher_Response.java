package com.thesis.scheduling.modellevel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class M_LeaveTeach_ShowAllTeacher_Response {
	
	@JsonProperty("id")
	private int leaveTeachId ;
	
	private String years;
    
	private String semester;
    
	private java.sql.Date dateStart;
    
	private java.sql.Date dateEnd;
	
	private String note;
}
