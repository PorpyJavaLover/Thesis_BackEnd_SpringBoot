package com.thesis.scheduling.modellevel.entity.id;

import java.io.Serializable;

import lombok.Data;

@Data
public class PlanID implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer years;
	private Integer semester;
	private Long courseId;
    private Long groupId;
}
