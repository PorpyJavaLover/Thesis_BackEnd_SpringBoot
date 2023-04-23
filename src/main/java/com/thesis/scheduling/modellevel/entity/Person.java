package com.thesis.scheduling.modellevel.entity;

import javax.persistence.Entity;

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
@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false , length = 10)
	private int personId;

    @ManyToOne
	@JoinColumn(name = "title_id", nullable = true)
    private Title titleId;

    @Column(length = 50)
	private String name;

	@Column( length = 50)
	private String lastname;

    @Column( length = 50)
    private String positionName;

	@ManyToOne
	@JoinColumn(name = "s_organization_id", nullable = true)
	private Organization sOrganizationId;
    
}
