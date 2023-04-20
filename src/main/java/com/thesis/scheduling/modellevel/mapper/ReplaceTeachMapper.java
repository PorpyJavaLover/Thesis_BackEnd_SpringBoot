package com.thesis.scheduling.modellevel.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.ReplaceTeach;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_PDFBodyTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_ReplaceTeach_PDFHeadTeacher_Response;
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
			targetSub.setDate(new Date(sourceTmp.getDate().getYear() + 543, sourceTmp.getDate().getMonth(),
					sourceTmp.getDate().getDate()));
			targetSub.setMemberTechingId(sourceTmp.getEssTimetableId().getMemberId().getMemberId());
			targetSub.setMemberTechingName(
					sourceTmp.getEssTimetableId().getMemberId().getTitleId().getTitleShort().toString() + " "
							+ sourceTmp.getEssTimetableId().getMemberId().getThFirstName().toString() + " "
							+ sourceTmp.getEssTimetableId().getMemberId().getThLastName().toString());
			targetSub.setMemberReplaceId(
					sourceTmp.getMemberReplaceId() == null ? null : sourceTmp.getMemberReplaceId().getMemberId());
			targetSub.setMemberReplaceName(sourceTmp.getMemberReplaceId() == null ? null
					: sourceTmp.getMemberReplaceId().getTitleId().getTitleShort().toString() + " "
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

	public M_ReplaceTeach_PDFHeadTeacher_Response toPDFHeadTeacher(ReplaceTeach source) {

		if (source == null) {
			return null;
		}

		M_ReplaceTeach_PDFHeadTeacher_Response target = new M_ReplaceTeach_PDFHeadTeacher_Response();
		target.setDateEnd(source.getLeaveTeachId().getDateEnd().getDate() + "");
		target.setDateStart(source.getLeaveTeachId().getDateStart().getDate() + "");
		target.setMonthEnd(convertMonth(source.getLeaveTeachId().getDateEnd().getMonth()));
		target.setMonthStart(convertMonth(source.getLeaveTeachId().getDateStart().getMonth()));
		target.setYearsEnd((source.getLeaveTeachId().getDateEnd().getYear() + 1900 + 543) + "");
		target.setYearsStart((source.getLeaveTeachId().getDateStart().getYear() + 1900 + 543) + "");
		target.setNameReplaceFirst(source.getMemberReplaceId().getThFirstName());
		target.setNameReplaceLast(source.getMemberReplaceId().getThLastName());
		target.setNameReplaceShort(source.getMemberReplaceId().getTitleId().getTitleShort());
		target.setNameTeachingFirst(source.getEssTimetableId().getMemberId().getThFirstName());
		target.setNameTeachingLast(source.getEssTimetableId().getMemberId().getThLastName());
		target.setNameTeachingShort(source.getEssTimetableId().getMemberId().getTitleId().getTitleShort());
		target.setNote(source.getLeaveTeachId().getNote());
		target.setOganize(source.getEssTimetableId().getMemberId().getSOrganizationId().getName());

		return target;
	}

	public Collection<M_ReplaceTeach_PDFBodyTeacher_Response> toPDFBodyTeacher(Iterable<ReplaceTeach> source) {

		if (source == null) {
			return null;
		}

		Collection<M_ReplaceTeach_PDFBodyTeacher_Response> target = new ArrayList<M_ReplaceTeach_PDFBodyTeacher_Response>();

		for (ReplaceTeach sourceTmp : source) {
			M_ReplaceTeach_PDFBodyTeacher_Response targetSub = new M_ReplaceTeach_PDFBodyTeacher_Response();
			targetSub.setCourseLect(sourceTmp.getEssTimetableId().getCourseId().getCourseLect().toString());
			targetSub.setCoursePerf(sourceTmp.getEssTimetableId().getCourseId().getCoursePerf().toString());
			targetSub.setCourseSum(String.valueOf(sourceTmp.getEssTimetableId().getCourseId().getCourseLect()
					+ sourceTmp.getEssTimetableId().getCourseId().getCoursePerf()));
			targetSub.setCourse_code(sourceTmp.getEssTimetableId().getCourseId().getCourse_code());
			targetSub.setCourse_title(sourceTmp.getEssTimetableId().getCourseId().getCourse_title());
			targetSub.setDate(convertDayOfWeek(sourceTmp.getDate().getDay()) + " " + sourceTmp.getDate().getDate() + " "
					+ convertSMonth(sourceTmp.getDate().getMonth()) + " " + (sourceTmp.getDate().getYear() + 1900 + 543));
			targetSub.setGroup_name(sourceTmp.getEssTimetableId().getGroupId().getGroup_name());
			targetSub.setTime(deconvertTime(sourceTmp.getEssTimetableId().getStartTime().toString()) + "-"
					+ (deconvertTime(sourceTmp.getEssTimetableId().getEndTime().toString()) - 1   ));
			target.add(targetSub);
		}

		return target;
	}

	public String convertDayOfWeek(int input) {
		String output = "วันใด";
		switch (input) {
			case 1:
				output = "จ.";
				break;
			case 2:
				output = "อ.";
				break;
			case 3:
				output = "พ.";
				break;
			case 4:
				output = "พฤ.";
				break;
			case 5:
				output = "ศ.";
				break;
			case 6:
				output = "ส.";
				break;
			case 7:
				output = "อา.";
				break;
		}
		return output;
	}

	public String convertMonth(int input) {
		String output = "วันใด";
		switch (input) {
			case 0:
				output = "มกราคม";
				break;
			case 1:
				output = "กุมภาพันธ์";
				break;
			case 2:
				output = "มีนาคม";
				break;
			case 3:
				output = "เมษายน";
				break;
			case 4:
				output = "พฤษภาคม";
				break;
			case 5:
				output = "มิถุนายน";
				break;
			case 6:
				output = "กรกฎาคม";
				break;
			case 7:
				output = "สิงหาคม";
				break;
			case 8:
				output = "กันยายน ";
				break;
			case 9:
				output = "ตุลาคม";
				break;
			case 10:
				output = "พฤศจิกายน";
				break;
			case 11:
				output = "ธันวาคม";
				break;
		}
		return output;
	}

	public String convertSMonth(int input) {
		String output = "วันใด";
		switch (input) {
			case 0:
				output = "ม.ค.";
				break;
			case 1:
				output = "ก.พ.";
				break;
			case 2:
				output = "มี.ค.";
				break;
			case 3:
				output = "เม.ย.";
				break;
			case 4:
				output = "พ.ค.";
				break;
			case 5:
				output = "มิ.ย.";
				break;
			case 6:
				output = "ก.ค.";
				break;
			case 7:
				output = "ส.ค.";
				break;
			case 8:
				output = "ก.ย. ";
				break;
			case 9:
				output = "ต.ค.";
				break;
			case 10:
				output = "พ.ย.";
				break;
			case 11:
				output = "ธ.ค.";
				break;
		}
		return output;
	}

	public int deconvertTime(String input) {
		int output = 0;
		switch (input) {
			case "08:00:00":
				output = 1;
				break;
			case "09:00:00":
				output = 2;
				break;
			case "10:00:00":
				output = 3;
				break;
			case "11:00:00":
				output = 4;
				break;
			case "12:00:00":
				output = 5;
				break;
			case "13:00:00":
				output = 6;
				break;
			case "14:00:00":
				output = 7;
				break;
			case "15:00:00":
				output = 8;
				break;
			case "16:00:00":
				output = 9;
				break;
			case "17:00:00":
				output = 10;
				break;
			case "18:00:00":
				output = 11;
				break;
			case "19:00:00":
				output = 12;
				break;
			case "20:00:00":
				output = 13;
				break;
			case "21:00:00":
				output = 14;
				break;
		}
		return output;
	}

	

}
