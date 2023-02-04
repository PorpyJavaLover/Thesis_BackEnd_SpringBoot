package com.thesis.scheduling.modellevel.model;

import java.sql.Time;

import lombok.Data;

@Data
public class M_Timetable_ShowTimeRemain_Response implements Comparable<M_Timetable_ShowTimeRemain_Response> {

	private Integer id;
	
	private String value;
	
	private String text;


	@Override
    public int compareTo(M_Timetable_ShowTimeRemain_Response value) {
        return this.getId().compareTo(value.getId());
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
