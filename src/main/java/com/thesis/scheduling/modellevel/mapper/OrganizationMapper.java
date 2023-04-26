package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;

@Component
public class OrganizationMapper {


	public Iterable<M_For_Selection_Response> toMShowForSelection(Iterable<Organization> source){

		if (source == null) {
			return null;
		}

		Collection<M_For_Selection_Response> target = new ArrayList<M_For_Selection_Response>();
		
		for (Organization sourceTmp : source) {

			M_For_Selection_Response targetSub = new M_For_Selection_Response();

			targetSub.setId(Integer.parseInt(sourceTmp.getCode()));
			targetSub.setValue(sourceTmp.getCode());
			targetSub.setText(sourceTmp.getName());
			target.add(targetSub);
		}
		
		return target;

	}
	
}
