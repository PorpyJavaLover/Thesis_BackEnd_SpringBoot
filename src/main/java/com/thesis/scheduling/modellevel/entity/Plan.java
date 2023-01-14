package com.thesis.scheduling.modellevel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.thesis.scheduling.modellevel.entity.id.PlanID;

import lombok.Data;

@Data
@Entity(name = "plan")
@IdClass(PlanID.class)
public class Plan {
	
    @Id
    @Column(name = "years" , length = 6)
	private Integer years;
    
    @Id
    @Column(name = "semester" , length = 2)
	private Integer semester;
    
    @Id
    @ManyToOne
	@JoinColumn(name = "COURSE_ID" , nullable = false)
    private Course courseId;
    
    @Column(name = "previous" , length = 10 )
    private Integer previous;

    @Id
    @ManyToOne
    @JoinColumn(name = "Group_ID" , nullable = false)
    private Group groupId;
   
}
