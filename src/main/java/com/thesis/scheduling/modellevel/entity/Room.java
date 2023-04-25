package com.thesis.scheduling.modellevel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "room")
public class Room {

	@Id
	@Column(name = "ROOM_ID", length = 6)
	private Integer roomId;

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "NSEAT", length = 3)
	private Integer seat;

	@OneToMany(mappedBy = "roomId")
	@Column(nullable = true)
	@JsonIgnore
	private List<Timetable> timetable;

	@PreRemove
	public void setForeignKeyToCascadeOnDelete() {
		for (Timetable s : timetable) {
			s.setRoomId(null);
		}
	}

	@PreUpdate
	public void setForeignKeyToNullOnUpdate() {
		for (Timetable s : timetable) {
			s.setRoomId(null);
		}
	}

}
