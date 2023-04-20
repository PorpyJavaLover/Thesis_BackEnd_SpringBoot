package com.thesis.scheduling.modellevel.model;

import java.sql.Date;

import lombok.Data;

@Data
public class M_ReplaceTeach_PDFHeadTeacher_Response {

    private String nameTeachingShort;
    private String nameTeachingFirst;
    private String nameTeachingLast;
    private String oganize;
    private String dateStart;
    private String monthStart;
    private String yearsStart;
    private String dateEnd;
    private String monthEnd;
    private String yearsEnd;
    private String note;
    private String nameReplaceShort;
    private String nameReplaceFirst;
    private String nameReplaceLast;

}
