package com.thesis.scheduling.modellevel.model;

import java.sql.Time;
import java.sql.Date;

import lombok.Data;

@Data
public class M_ReplaceTeach_ShowAllTeacher_Response {

	private int replaceTeachId;

    private int leaveTeachId ;

    private String course_code;

    private String course_title;

    private String group_name;

    private Time start_time;

    private Time end_time;

    private Date date;

    private Integer memberTechingId;

    private String memberTechingName;

    private Integer memberReplaceId;

    private String memberReplaceName;

}
