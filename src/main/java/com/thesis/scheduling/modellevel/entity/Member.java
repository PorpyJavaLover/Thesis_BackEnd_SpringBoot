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
@Entity(name = "member")
public class Member{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, updatable = true , length = 10)
    private Integer memberId;

    @ManyToOne
	@JoinColumn(name = "title_id", nullable = true)
    private Title titleId;
    
	@Column(length = 50)
	private String thFirstName;

	@Column(length = 50)
	private String thLastName;

	@Column(length = 50)
	private String enFirstName;

	@Column(length = 50)
	private String enLastName;

	@Column(length = 25)
	private String username;

	@Column(length = 25)
	private String password;

	@ManyToOne
	@JoinColumn(name = "s_organization_id", nullable = true)
	private Organization organizationId;
	
	@Column(length = 10)
	private Integer role;

    @Column
	private boolean activeStatus;
	
    //<<JOIN ZONE>>
	
    @OneToMany(mappedBy = "memberId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Timetable> timetable;
    
    @OneToMany(mappedBy = "memberId")
    @Column(nullable = true)
    @JsonIgnore
    private List<NotTeach> notteach;
    
    @OneToMany(mappedBy = "memberReplaceId")
    @Column(nullable = true)
    @JsonIgnore
    private List<ReplaceTeach> replaceTeach;
    
    @OneToMany(mappedBy = "memberId")
    @Column(nullable = true)
    @JsonIgnore
    private List<LeaveTeach> leaveTeach;



}
