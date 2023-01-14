package com.thesis.scheduling.modellevel.entity;

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
@Entity(name = "leave_teach")
public class LeaveTeach {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEAVE_TEACH_ID" ,nullable = false, updatable = false , length = 10)
	private int leaveTeachId;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID" , nullable = false)
    private Member memberId;
	
    @Column(name = "YEARS")
	private String years;
    
    @Column(name = "SEMESTER")
	private String semester;
    
    @Column(name = "DATE_START")
	private java.sql.Date dateStart;
    
	@Column(name = "DATE_END")
	private java.sql.Date dateEnd;
	
    @Column(name = "NOTE")
	private String note;
	
    //<<JOIN ZONE>>
    
    @OneToMany(mappedBy = "leaveTeachId")
    @Column(nullable = true)
    @JsonIgnore
    private List<ReplaceTeach> replaceTeach;
	
	
	
}
