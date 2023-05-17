package com.thesis.scheduling.modellevel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "s_organization")
public class Organization {

	public String getCode() {
		return code;
	}

	public String getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public int getLevel() {
		return level;
	}

	public int getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}

	public List<Member> getMember() {
		return member;
	}

	public List<Person> getPerson() {
		return person;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

	public Organization(String code, String parent, String name, String nameEn, int level, int type, int status,
			List<Member> member, List<Person> person, List<Room> room) {
		this.code = code;
		this.parent = parent;
		this.name = name;
		this.nameEn = nameEn;
		this.level = level;
		this.type = type;
		this.status = status;
		this.member = member;
		this.person = person;
		this.room = room;
	}

	public Organization() {
	}

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
