package com.thesis.scheduling.modellevel.model;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Timetable;

import lombok.Data;

@Data
public class M_ReplaceTeach_ShowAllTeacher_Response {

	
	private int replaceTeachId;
	
    private LeaveTeach leaveTeachId;
	
    private Timetable essTimetableId;
	
    private Member memberReplaceId;
    
}
