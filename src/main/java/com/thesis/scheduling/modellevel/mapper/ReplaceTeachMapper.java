package com.thesis.scheduling.modellevel.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_SelectOption_Response;

@Component
public class ReplaceTeachMapper {

	public Iterable<M_ReplaceTeach_ShowAllTeacher_Response> toMShowAllTeacher(Iterable<ReplaceTeach> source) {

		if (source == null) {
			return null;
		}

		Collection<M_ReplaceTeach_ShowAllTeacher_Response> target = new ArrayList<M_ReplaceTeach_ShowAllTeacher_Response>();

		for (ReplaceTeach sourceTmp : source) {
			M_ReplaceTeach_ShowAllTeacher_Response targetSub = new M_ReplaceTeach_ShowAllTeacher_Response();
			targetSub.setReplaceTeachId(sourceTmp.getReplaceTeachId());
			targetSub.setLeaveTeachId(sourceTmp.getLeaveTeachId().getLeaveTeachId());
			targetSub.setCourse_code(sourceTmp.getEssTimetableId().getCourseId().getCourse_code());
			targetSub.setCourse_title(sourceTmp.getEssTimetableId().getCourseId().getCourse_title());
			targetSub.setGroup_name(sourceTmp.getEssTimetableId().getGroupId().getGroup_name());
			targetSub.setStart_time(sourceTmp.getEssTimetableId().getStartTime());
			targetSub.setEnd_time(sourceTmp.getEssTimetableId().getEndTime());
			targetSub.setDate(new Date(sourceTmp.getDate().getYear() + 543,sourceTmp.getDate().getMonth(), sourceTmp.getDate().getDate()));
			targetSub.setMemberTechingId(sourceTmp.getEssTimetableId().getMemberId().getMemberId());
			targetSub.setMemberTechingName(
					sourceTmp.getEssTimetableId().getMemberId().getTitleId().getTitleShort().toString() + " "
							+ sourceTmp.getEssTimetableId().getMemberId().getThFirstName().toString() + " "
							+ sourceTmp.getEssTimetableId().getMemberId().getThLastName().toString());
			targetSub.setMemberReplaceId(
					sourceTmp.getMemberReplaceId() == null ? null : sourceTmp.getMemberReplaceId().getMemberId());
			targetSub.setMemberReplaceName(sourceTmp.getMemberReplaceId() == null ? null : sourceTmp.getMemberReplaceId().getTitleId().getTitleShort().toString() + " "
					+ sourceTmp.getMemberReplaceId().getThFirstName().toString() + " "
					+ sourceTmp.getMemberReplaceId().getThLastName().toString());
			target.add(targetSub);
		}

		return target;
	}

	public Iterable<M_SelectOption_Response> toMemberReplaceOptionTeacher(Iterable<Member> source) {

		if (source == null) {
			return null;
		}

		Collection<M_SelectOption_Response> target = new ArrayList<M_SelectOption_Response>();

		for (Member sourceTmp : source) {
			M_SelectOption_Response targetSub = new M_SelectOption_Response();
			targetSub.setId(sourceTmp.getMemberId());
			targetSub.setValue(sourceTmp.getMemberId().toString());
			targetSub.setText(sourceTmp.getTitleId().getTitleShort().toString() + " "
			+ sourceTmp.getThFirstName().toString() + " "
			+ sourceTmp.getThLastName().toString());
			target.add(targetSub);
		}
		
		return target;
	}

}
