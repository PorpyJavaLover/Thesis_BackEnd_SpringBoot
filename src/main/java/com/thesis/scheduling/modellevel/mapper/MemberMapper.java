package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Response;
import com.thesis.scheduling.modellevel.model.M_Member_ShowAllStaff_Response;

@Component
public class MemberMapper {

	public M_Member_Register_Response toMRegisterResponse(Member source) {
		if (source == null) {
			return null;
		}

		M_Member_Register_Response target = new M_Member_Register_Response();
		
		return target;
	}

	public Iterable<M_Member_ShowAllStaff_Response> toMShowAll(Iterable<Member> source) {

		if (source == null) {
			return null;
		}

		Collection<M_Member_ShowAllStaff_Response> target = new ArrayList<M_Member_ShowAllStaff_Response>();
		
		for (Member sourceTmp : source) {
			M_Member_ShowAllStaff_Response targetSub = new M_Member_ShowAllStaff_Response();
			targetSub.setId(sourceTmp.getMemberId());
			targetSub.setValue(sourceTmp.getMemberId().toString());
			targetSub.setText(sourceTmp.getTitleId().getTitleShort()+" "+sourceTmp.getThFirstName()+" "+sourceTmp.getThLastName());
			target.add(targetSub);
		}
		
		return target;
		
	}

}
