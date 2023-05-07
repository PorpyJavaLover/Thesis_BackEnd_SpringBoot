package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Member_Organiz_Response;

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

	public Iterable<M_For_Selection_Response> toMShowMemberForOption(Iterable<Member> source) {

		if (source == null) {
			return null;
		}

		Collection<M_For_Selection_Response> target = new ArrayList<M_For_Selection_Response>();

		for (Member sourceTmp : source) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceTmp.getMemberId());
			targetSub.setValue(sourceTmp.getMemberId().toString());
			targetSub.setText(sourceTmp.getTitleId().getTitleShort() + " " + sourceTmp.getThFirstName() + " "
					+ sourceTmp.getThLastName());
			target.add(targetSub);
		}

		return target;

	}

	public M_Member_Organiz_Response toMShowMemberOrganiz(Member sourceA , Organization sourceB) {

		if (sourceA == null) {
			return null;
		}

		M_Member_Organiz_Response target = new M_Member_Organiz_Response();

		target.setOrganiz(sourceA.getOrganizationId().getCode());
		target.setFaculty(sourceB.getParent());

		return target;

	}

	
}
