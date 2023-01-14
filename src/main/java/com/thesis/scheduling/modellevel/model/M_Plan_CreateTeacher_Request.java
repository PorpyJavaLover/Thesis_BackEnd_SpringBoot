package com.thesis.scheduling.modellevel.model;

import com.thesis.scheduling.modellevel.entity.Course;
import com.thesis.scheduling.modellevel.entity.Group;

import lombok.Data;

@Data
public class M_Plan_CreateTeacher_Request {

	private int years;
    
	private int semester;
    
    private Course courseId;

    private Group groupId;
}
