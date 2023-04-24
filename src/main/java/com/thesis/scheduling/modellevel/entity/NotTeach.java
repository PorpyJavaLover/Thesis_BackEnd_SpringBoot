package com.thesis.scheduling.modellevel.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "not_teach")
public class NotTeach {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false , length = 10)
	private int notId;
	
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberId;
    
	@Column(length = 10)
	private int dayOfWeek;
	
	@Column(length = 10)
	private Time timeStart;
	
	@Column(length = 10)
	private Time timeEnd;

	@Column(length = 10)
	private String years;

    @Column(length = 10)
	private String semester;
    
    
    
    
	
	
}
