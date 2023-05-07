package com.thesis.scheduling.modellevel.model;

import lombok.Data;

@Data
public class M_Timetable_ShowTable_Response {

    private Integer index;
    private Integer activeStatus;
    private String course_code;
    private String course_title;
    private String group_name;
    private Integer courseLect;
    private Integer coursePerf;
    private Integer timeLect;
    private Integer timePerf;
    private Integer day_of_week;
    private String room_name;
    private String member_name;

}
