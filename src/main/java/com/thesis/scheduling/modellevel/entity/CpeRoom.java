package com.thesis.scheduling.modellevel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "cpe_room")
public class CpeRoom {

	@Id
	@Column(name = "ROOM_ID", length = 6)
	private Integer roomId;

	@Column(name = "BUILDING_ID", length = 6)
	private Integer buildId;

	@Column(name = "TYPE_ID", length = 3)
	private Integer typeId;

	@Column(name = "ROOM_NO", length = 20)
	private String roomNo;

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "NSEAT", length = 3)
	private Integer nseat;

	@Column(name = "STATUS", length = 1)
	private Integer status;
	
    @OneToMany(mappedBy = "roomId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Timetable> timetable;

	public void setForeignKeyToCascadeOnDelete() {
		for (Timetable s : timetable) {
        	s.setRoomId(null);
        }
		
	}

	public void setForeignKeyToNullOnUpdate() {
		for (Timetable s : timetable) {
        	s.setRoomId(null);
        }
	}
	
}
