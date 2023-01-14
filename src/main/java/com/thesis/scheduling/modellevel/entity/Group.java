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
@Entity(name = "groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Group_ID")
	private Long groupId;

	@Column(name = "Group_Name")
	private String group_name;

	@OneToMany(mappedBy = "groupId")
	@Column(nullable = true)
	@JsonIgnore
	private List<Plan> plan;

	@OneToMany(mappedBy = "groupId")
	@Column(nullable = true)
	@JsonIgnore
	private List<Timetable> timetable;

}
