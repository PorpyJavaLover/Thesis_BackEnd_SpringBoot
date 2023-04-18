package com.thesis.scheduling.modellevel.model;

import com.thesis.scheduling.modellevel.entity.LeaveTeach;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Timetable;

import lombok.Data;

@Data
public class M_ReplaceTeach_ShowAllTeacher_Response {

	private int replaceTeachId;

    private int leaveTeachId ;

    private String course_code;

    private String course_title;

    private String group_name;

    private java.sql.Date date;

    private Integer memberTechingId;

    private String memberTechingName;

    private Integer memberReplaceId;

    private String memberReplaceName;

}
