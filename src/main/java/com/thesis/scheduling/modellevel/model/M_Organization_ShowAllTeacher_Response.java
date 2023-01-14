package com.thesis.scheduling.modellevel.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class M_Organization_ShowAllTeacher_Response {
	

	@JsonProperty("value")
	public String code;
	
	@JsonProperty("text")
	public String name;



}
