package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;

@Component
public class GroupMapper {

    public Iterable<M_For_Selection_Response> toMShowForSelection(Iterable<Group> source){

		if (source == null) {
			return null;
		}

		Collection<M_For_Selection_Response> target = new ArrayList<M_For_Selection_Response>();
		
		for (Group sourceTmp : source) {

			M_For_Selection_Response targetSub = new M_For_Selection_Response();

			targetSub.setId(sourceTmp.getGroupId().intValue());
			targetSub.setValue(sourceTmp.getGroupId().toString());
			targetSub.setText(sourceTmp.getGroup_name());
			target.add(targetSub);
		}
		
		return target;

	}
    
}
