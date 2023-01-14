package com.thesis.scheduling.modellevel.entity.id;

import java.io.Serializable;

import lombok.Data;

@Data
public class TimetableID implements Serializable {

	private static final long serialVersionUID = 1L;
	private String years;
	private String semester;
	private Long courseId;
    private Long groupId;
    private int memberId;
}
