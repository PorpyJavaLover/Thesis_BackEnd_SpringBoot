package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Title;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;

@Component
public class TitleMapper {
	
	public Iterable<M_For_Selection_Response> toMShowTitleOption(Iterable<Title> source){
		
		if (source == null) {
			return null;
		}

		Collection<M_For_Selection_Response> target = new ArrayList<M_For_Selection_Response>();
		
		for (Title sourceTmp : source) {

			M_For_Selection_Response targetSub = new M_For_Selection_Response();

			targetSub.setId(sourceTmp.getTitleId().intValue());
			targetSub.setValue(sourceTmp.getTitleId().toString());
			targetSub.setText(sourceTmp.getTitleName());
			target.add(targetSub);
		}
		
		return target;
	}
	
}
