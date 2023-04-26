package com.thesis.scheduling.modellevel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( length = 6)
	private Integer roomId;

	@Column( length = 50)
	private String roomName;

	@Column(length = 3)
	private Integer seat;

	@ManyToOne
	@JoinColumn(name = "s_organization_id", nullable = true)
	private Organization organizationId;

	@Column
	private boolean status;

	//<<JOIN ZONE>>

	@OneToMany(mappedBy = "roomId")
	@Column(nullable = true)
	@JsonIgnore
	private List<Timetable> timetable;

}
