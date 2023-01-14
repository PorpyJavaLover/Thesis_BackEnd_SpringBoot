package com.thesis.scheduling.modellevel.model;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Timetable;

import lombok.Data;

@Data
public class M_ReplaceTeach_CreateTeacher_Request {

    private LeaveTeach leaveTeachId;
	
    private Timetable essTimetableId;
    
}
