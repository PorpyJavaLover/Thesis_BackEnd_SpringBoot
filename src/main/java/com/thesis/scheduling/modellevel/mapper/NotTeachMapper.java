package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.NotTeach;
import com.thesis.scheduling.modellevel.model.M_NotTeach_ShowAllTeacher_Response;

@Component
public class NotTeachMapper {

	public Iterable<M_NotTeach_ShowAllTeacher_Response> toMShowAllTeacher(Iterable<NotTeach> source){

		if (source == null) {
			return null;
		}

		Collection<M_NotTeach_ShowAllTeacher_Response> target = new ArrayList<M_NotTeach_ShowAllTeacher_Response>();

		return target;
	}
}