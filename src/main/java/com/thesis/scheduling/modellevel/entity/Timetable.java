package com.thesis.scheduling.modellevel.entity;

import java.sql.Time;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity(name = "ess_timetable")
public class Timetable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIMETABLE_ID",nullable = false, updatable = false , length = 10)
	private Integer timetableId;
	
    @Column(name = "YEARNO")
	private String years;
	

    @Column(name = "SEMESTER_ID")
	private String semester;
    
    @ManyToOne
	@JoinColumn(name = "COURSE_ID" , nullable = false)
    private Course courseId;

    @ManyToOne
    @JoinColumn(name = "GROUP_NO" , nullable = false)
    private Group groupId;
    
    @ManyToOne
    @JoinColumn(name = "INSTRUCTOR_ID" , nullable = true)
    private Member memberId;
    
    @ManyToOne
    @JoinColumn(name = "ROOM_ID" , nullable = true)
	private Room roomId;
    
    @Column(length = 2 , name = "DAY_OF_WEEK")
	private Integer dayOfWeek;
    
	@Column(name = "START_TIME" )
	private Time startTime;
	
	@Column(name = "END_TIME")
	private Time endTime;
	
	@Column(name = "TIME_LOCKER" , columnDefinition = "BOOLEAN DEFAULT false" )
	private boolean timeLocker;
	
	@Column(name = "ROOM_LOCKER" , columnDefinition = "BOOLEAN DEFAULT false")
	private boolean roomLocker;
	
	@Column(name = "COURSE_TYPE" )
	private Integer courseType;
    
    //<<JOIN ZONE>>
    
    @OneToMany(mappedBy = "essTimetableId")
    @Column(nullable = true)
    @JsonIgnore
    private List<ReplaceTeach> replaceteach;
    
}
