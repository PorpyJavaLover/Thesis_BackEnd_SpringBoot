package com.thesis.scheduling.modellevel.entity;

import java.io.Serializable;
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
@Entity(name = "title")
public class Title implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
	private Long titleId;
	
	private String titleName;

	private String titleShort;
	
    @OneToMany(mappedBy = "titleId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Member> member;

    @OneToMany(mappedBy = "titleId")
    @Column(nullable = true)
    @JsonIgnore
    private List<Person> person;
	
}
