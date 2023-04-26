package com.thesis.scheduling.modellevel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "s_organization")
public class Organization {

	@Id
	@Column(name = "code", nullable = false, length = 8)
	private String code;

	@Column(name = "parent", nullable = false, length = 8)
	private String parent;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "level", nullable = false, length = 11)
	int level;

	@Column(name = "type", nullable = false, length = 11)
	int type;

	@Column(name = "status", nullable = false, length = 11)
	int status;
	
	//JOIN ZONE 
	
    @OneToMany(mappedBy = "organizationId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Member> member;

	@OneToMany(mappedBy = "organizationId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Person> person;

	@OneToMany(mappedBy = "organizationId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Room> room;

}
