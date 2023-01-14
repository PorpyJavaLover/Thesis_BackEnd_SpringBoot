package com.thesis.scheduling.modellevel.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "ess_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long courseId;
    
    @Column(name = "COURSE_CODE")
    private String course_code;
    
    @Column(name = "COURSE_TITLE")
    private String course_title;
    
    @Column(name = "COURSE_TITLE_EN")
    private String course_title_en;
    
    @Column(name = "LECT_TIME")
    private Integer courseLect;
    
    @Column(name = "PERF_TIME")
    private Integer coursePerf;
    
    @OneToMany(mappedBy = "courseId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Plan> plan;
    
    @OneToMany(mappedBy = "courseId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Timetable> timetable;
        
}
