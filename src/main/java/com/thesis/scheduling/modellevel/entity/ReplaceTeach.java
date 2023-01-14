package com.thesis.scheduling.modellevel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "replace_teach")
public class ReplaceTeach {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLACE_TEACH_ID" ,nullable = false, updatable = false , length = 10)
	private int replaceTeachId;
	
	@ManyToOne
    @JoinColumn(name = "LEAVE_TEACH_ID", nullable = false)
    private LeaveTeach leaveTeachId;
	
	@ManyToOne
    @JoinColumn(name = "ESS_TIMETABLE_ID", nullable = false)
    private Timetable essTimetableId;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_REPLACE_ID" , nullable = false)
    private Member memberReplaceId;
    
}
