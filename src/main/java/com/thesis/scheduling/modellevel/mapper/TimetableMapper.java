package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.NotTeach;
import com.thesis.scheduling.modellevel.entity.Room;
import com.thesis.scheduling.modellevel.entity.Timetable;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllStaff_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowAllTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowMemberTimeTeacher_Response;
import com.thesis.scheduling.modellevel.model.M_Timetable_ShowTimeRemain_Response;

@Component
public class TimetableMapper {

	public Iterable<M_Timetable_ShowAllTeacher_Response> toMTimetableTeacher(Iterable<Timetable> source) {

		if (source == null) {
			return null;
		}

		Collection<M_Timetable_ShowAllTeacher_Response> target = new ArrayList<M_Timetable_ShowAllTeacher_Response>();

		for (Timetable sourceTmp : source) {
			M_Timetable_ShowAllTeacher_Response targetSub = new M_Timetable_ShowAllTeacher_Response();
			targetSub.setYears(sourceTmp.getYears().toString());
			targetSub.setSemester(sourceTmp.getSemester().toString());
			targetSub.setCourse_id(sourceTmp.getCourseId().getCourseId().toString());
			targetSub.setCourse_type(sourceTmp.getCourseType());
			targetSub.setGroup_id(sourceTmp.getGroupId().getGroupId().toString());
			targetSub.setMember_id(sourceTmp.getMemberId().getMemberId().toString());
			target.add(targetSub);
		}

		return target;

	}

	public M_Timetable_ShowAllStaff_Response toMTimetableStaff(Timetable sourceA , Collection<Timetable> sourceB) {

		if (sourceA == null) {
			return null;
		}

		M_Timetable_ShowAllStaff_Response target = new M_Timetable_ShowAllStaff_Response();
		target.setId(sourceA.getTimetableId());
		target.setYears(sourceA.getYears().toString());
		target.setSemester(sourceA.getSemester().toString());
		target.setCourse_id(sourceA.getCourseId().getCourseId().toString());
		target.setCourse_name(sourceA.getCourseId().getCourse_title().toString());
		target.setCourse_code(sourceA.getCourseId().getCourse_code().toString());
		target.setCourse_type(sourceA.getCourseType());
		target.setCourse_type_name(sourceA.getCourseType() == 0 ? "ทฤษฎี" : "ปฏิบัติ");
		target.setGroup_id(sourceA.getGroupId().getGroupId().toString());
		target.setGroup_name(sourceA.getGroupId().getGroup_name().toString());
		target.setDay_of_week(sourceA.getDayOfWeek() == null ? "null" : sourceA.getDayOfWeek().toString());
		target.setDay_of_week_name(sourceA.getDayOfWeek() == null ? "null" : convertDayOfWeek(sourceA.getDayOfWeek()));
		target.setStart_time(sourceA.getStartTime() == null ? "null" : sourceA.getStartTime().toString());
		target.setEnd_time(sourceA.getEndTime() == null ? "null" : sourceA.getEndTime().toString());
		target.setRoom_id(sourceA.getRoomId() == null ? "null" : sourceA.getRoomId().getRoomId().toString());
		target.setRoom_name(sourceA.getRoomId() == null ? "null" : sourceA.getRoomId().getRoomName().replace("  ", " "));
		target.setTime_locker(sourceA.isTimeLocker());
		target.setRoom_locker(sourceA.isRoomLocker());
		Collection<HashMap<String, String>> cMember = new ArrayList<>();
		String member_name = "";
		for(Timetable sourceBTmp :sourceB){

			HashMap<String, String> cSubMember = new HashMap<String, String>();
			cSubMember.put("member_id" , sourceBTmp.getMemberId().getMemberId().toString());
			cSubMember.put("member_name" , sourceBTmp.getMemberId().getTitleId().getTitleShort().toString() + " "
			+ sourceBTmp.getMemberId().getThFirstName().toString() + " "
			+ sourceBTmp.getMemberId().getThLastName().toString());
			cMember.add(cSubMember);

			member_name = member_name + " " + sourceBTmp.getMemberId().getTitleId().getTitleShort().toString() + " "
			+ sourceBTmp.getMemberId().getThFirstName().toString() + " "
			+ sourceBTmp.getMemberId().getThLastName().toString();

		}
		target.setMember(cMember);
		target.setMember_Id(sourceA.getMemberId().getMemberId());
		target.setMember_name(member_name);
		return target;
	}

	public Iterable<M_Timetable_ShowMemberTimeTeacher_Response> toMMemberTimeTeacher(Iterable<Timetable> source) {

		if (source == null) {
			return null;
		}

		Collection<M_Timetable_ShowMemberTimeTeacher_Response> target = new ArrayList<M_Timetable_ShowMemberTimeTeacher_Response>();

		for (Timetable sourceTmp : source) {
			M_Timetable_ShowMemberTimeTeacher_Response targetSub = new M_Timetable_ShowMemberTimeTeacher_Response();
			targetSub.setId(sourceTmp.getMemberId().getMemberId());
			targetSub.setDay_of_week(sourceTmp.getDayOfWeek());
			targetSub.setStart_time(sourceTmp.getStartTime());
			targetSub.setEnd_time(sourceTmp.getEndTime());
			target.add(targetSub);
		}

		return target;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> getMTimeStartBase() {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		for (int i = 1; i <= 14; i++) {
			M_Timetable_ShowTimeRemain_Response targetSubA = new M_Timetable_ShowTimeRemain_Response();
			targetSubA.setId(i);
			targetSubA.setValue(convertStartTime(i));
			targetSubA.setText(convertStartTime(i));
			targetA.add(targetSubA);
		}
		return targetA;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeStartCoTeach(Iterable<Timetable> sourceA) {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		for (Timetable sourceATmp : sourceA) {

			int interim = deconvertStartTime(sourceATmp.getEndTime().toString())
					- deconvertStartTime(sourceATmp.getStartTime().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubA = new M_Timetable_ShowTimeRemain_Response();
				targetSubA.setId(deconvertStartTime(sourceATmp.getStartTime().toString()) + (interim - 1 - i));
				targetSubA.setValue(
						convertStartTime(deconvertStartTime(sourceATmp.getStartTime().toString()) + (interim - 1 - i)));
				targetSubA.setText(
						convertStartTime(deconvertStartTime(sourceATmp.getStartTime().toString()) + (interim - 1 - i)));
				targetA.add(targetSubA);
			}
		}
		return targetA;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeStartInconvenient(Collection<NotTeach> sourceA) {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		for (NotTeach sourceATmp : sourceA) {

			int interim = deconvertStartTime(sourceATmp.getTimeEnd().toString())
					- deconvertStartTime(sourceATmp.getTimeStart().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubA = new M_Timetable_ShowTimeRemain_Response();
				targetSubA.setId(deconvertStartTime(sourceATmp.getTimeStart().toString()) + (interim - 1 - i));
				targetSubA.setValue(
						convertStartTime(deconvertStartTime(sourceATmp.getTimeStart().toString()) + (interim - 1 - i)));
				targetSubA.setText(
						convertStartTime(deconvertStartTime(sourceATmp.getTimeStart().toString()) + (interim - 1 - i)));
				targetA.add(targetSubA);
			}
		}
		return targetA;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeStartEachClassToDay(Collection<Timetable> sourceA) {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		for (Timetable sourceSubA : sourceA) {
			Integer j = 0;
			if (sourceSubA.getCourseType() == 0) {
				j = sourceSubA.getCourseId().getCourseLect();
			} else if (sourceSubA.getCourseType() == 1) {
				j = (sourceSubA.getCourseId().getCoursePerf());
			}
			for (int i = 0; i < j; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubA = new M_Timetable_ShowTimeRemain_Response();
				targetSubA.setId(deconvertStartTime(sourceSubA.getStartTime().toString()) + i);
				targetSubA.setValue(convertStartTime(deconvertStartTime(sourceSubA.getStartTime().toString()) + i));
				targetSubA.setText(convertStartTime(deconvertStartTime(sourceSubA.getStartTime().toString()) + i));
				targetA.add(targetSubA);
			}
		}
		return targetA;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> getMTimeEndBase() {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		for (int i = 1; i <= 14; i++) {
			M_Timetable_ShowTimeRemain_Response targetSubA = new M_Timetable_ShowTimeRemain_Response();
			targetSubA.setId(i);
			targetSubA.setValue(convertEndTime(i));
			targetSubA.setText(convertEndTime(i));
			targetA.add(targetSubA);
		}
		return targetA;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeEndCoTeach(Iterable<Timetable> sourceA) {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (Timetable sourceATmp : sourceA) {

			int interim = deconvertEndTime(sourceATmp.getEndTime().toString())
					- deconvertEndTime(sourceATmp.getStartTime().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetASub = new M_Timetable_ShowTimeRemain_Response();
				targetASub.setId(deconvertEndTime(sourceATmp.getStartTime().toString()) + (interim - i));
				targetASub.setValue(
						convertEndTime(deconvertEndTime(sourceATmp.getStartTime().toString()) + (interim - i)));
				targetASub
						.setText(
								convertEndTime(deconvertEndTime(sourceATmp.getStartTime().toString()) + (interim - i)));
				targetA.add(targetASub);
			}
		}

		return targetA;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeEndInconvenient(Collection<NotTeach> sourceA) {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (NotTeach sourceATmp : sourceA) {

			int interim = deconvertEndTime(sourceATmp.getTimeEnd().toString())
					- deconvertEndTime(sourceATmp.getTimeStart().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetASub = new M_Timetable_ShowTimeRemain_Response();
				targetASub.setId(deconvertEndTime(sourceATmp.getTimeStart().toString()) + (interim - i));
				targetASub.setValue(
						convertEndTime(deconvertEndTime(sourceATmp.getTimeStart().toString()) + (interim - i)));
				targetASub
						.setText(
								convertEndTime(deconvertEndTime(sourceATmp.getTimeStart().toString()) + (interim - i)));
				targetA.add(targetASub);
			}
		}
		return targetA;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeEndEachClassToDay(Collection<Timetable> sourceA) {

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (Timetable sourceATmp : sourceA) {
			Integer j = 0;
			if (sourceATmp.getCourseType() == 0) {
				j = sourceATmp.getCourseId().getCourseLect();
			} else if (sourceATmp.getCourseType() == 1) {
				j = (sourceATmp.getCourseId().getCoursePerf());
			}
			for (int i = 0; i < j ; i++) {
				M_Timetable_ShowTimeRemain_Response targetASub = new M_Timetable_ShowTimeRemain_Response();
				targetASub.setId(deconvertEndTime(sourceATmp.getEndTime().toString()) - i);
				targetASub.setValue(convertEndTime(deconvertEndTime(sourceATmp.getEndTime().toString()) - i));
				targetASub.setText(convertEndTime(deconvertEndTime(sourceATmp.getEndTime().toString()) - i));
				targetA.add(targetASub);
			}
		}
		return targetA;
	}

	public M_Timetable_ShowTimeRemain_Response toMTimeStartStaff(Timetable sourceA,
			String Endtime) {

		if (sourceA == null) {
			return null;
		}

		M_Timetable_ShowTimeRemain_Response targetA = new M_Timetable_ShowTimeRemain_Response();

		int x, y, z;

		if (sourceA.getCourseType() == 0) {
			x = sourceA.getCourseId().getCourseLect();
		} else {
			x = (sourceA.getCourseId().getCoursePerf());
		}

		y = deconvertEndTime(Endtime);
		z = y - x + 1;

		targetA.setId(z);
		targetA.setValue(convertStartTime(z));
		targetA.setText(convertStartTime(z));

		return targetA;
	}

	public M_Timetable_ShowTimeRemain_Response toMTimeEndStaff(Timetable sourceA,
			String startTime) {

		if (sourceA == null) {
			return null;
		}

		M_Timetable_ShowTimeRemain_Response targetA = new M_Timetable_ShowTimeRemain_Response();

		int x, y, z;

		if (sourceA.getCourseType() == 0) {
			x = sourceA.getCourseId().getCourseLect();
		} else {
			x = (sourceA.getCourseId().getCoursePerf());
		}

		y = deconvertStartTime(startTime);
		z = x + y - 1;

		targetA.setId(z);
		targetA.setValue(convertEndTime(z));
		targetA.setText(convertEndTime(z));

		return targetA;
	}

	public Collection<M_For_Selection_Response> toMRoomStaff(boolean modeForAutoPilot, Iterable<Timetable> sourceD,
			Iterable<Room> sourceA) {

		if (sourceD == null) {
			return null;
		}

		Collection<M_For_Selection_Response> targetD = new ArrayList<M_For_Selection_Response>();

		for (Timetable sourceDTmp : sourceD) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceDTmp.getRoomId().getRoomId());
			targetSub.setValue(sourceDTmp.getRoomId().getRoomId().toString());
			targetSub.setText(sourceDTmp.getRoomId().getRoomName());
			targetD.add(targetSub);
		}

		Collection<M_For_Selection_Response> targetA = new ArrayList<M_For_Selection_Response>();

		for (Room sourceATmp : sourceA) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceATmp.getRoomId());
			targetSub.setValue(sourceATmp.getRoomId().toString());
			targetSub.setText(sourceATmp.getRoomName());
			targetA.add(targetSub);
		}

		Collection<M_For_Selection_Response> targetE;

		if (modeForAutoPilot) {
			targetE = UnionRoomForAuto(targetA, targetD);
		} else {
			targetE = UnionRoom(targetA, targetD);
		}

		return targetE;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> UnionDayAndTime(
			Collection<M_Timetable_ShowTimeRemain_Response> cSum, Collection<M_Timetable_ShowTimeRemain_Response> cA,
			Collection<M_Timetable_ShowTimeRemain_Response> cB, Collection<M_Timetable_ShowTimeRemain_Response> cC) {

		cSum.removeAll(cA);
		cSum.removeAll(cB);
		cSum.removeAll(cC);

		Collection<M_Timetable_ShowTimeRemain_Response> AB = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cA);
		AB.addAll(cB);
		AB.retainAll(cA);
		AB.retainAll(cB);

		Collection<M_Timetable_ShowTimeRemain_Response> BC = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cB);
		BC.addAll(cC);
		BC.retainAll(cB);
		BC.retainAll(cC);

		Collection<M_Timetable_ShowTimeRemain_Response> AC = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cA);
		AC.addAll(cC);
		AC.retainAll(cA);
		AC.retainAll(cC);

		Collection<M_Timetable_ShowTimeRemain_Response> ABC = new TreeSet<M_Timetable_ShowTimeRemain_Response>(AB);
		ABC.addAll(cC);
		ABC.retainAll(cA);
		ABC.retainAll(cB);
		ABC.retainAll(cC);

		AB.removeAll(ABC);
		BC.removeAll(ABC);
		AC.removeAll(ABC);

		Collection<M_Timetable_ShowTimeRemain_Response> A = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cA);
		A.removeAll(AB);
		A.removeAll(AC);
		A.removeAll(ABC);

		Collection<M_Timetable_ShowTimeRemain_Response> B = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cB);
		B.removeAll(AB);
		B.removeAll(BC);
		B.removeAll(ABC);

		Collection<M_Timetable_ShowTimeRemain_Response> C = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cC);
		C.removeAll(AC);
		C.removeAll(BC);
		C.removeAll(ABC);

		for (M_Timetable_ShowTimeRemain_Response ASub : A) {
			ASub.setText("🟡 " + ASub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response BSub : B) {
			BSub.setText("⚪️ " + BSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response CSub : C) {
			CSub.setText("🔴 " + CSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response ABSub : AB) {
			ABSub.setText("🟡⚪️ " + ABSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response ACSub : AC) {
			ACSub.setText("🔴🟡 " + ACSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response BCSub : BC) {
			BCSub.setText("🔴⚪️ " + BCSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response ABCSub : ABC) {
			ABCSub.setText("🔴🟡⚪️ " + ABCSub.getText());
		}

		cSum.addAll(A);
		cSum.addAll(B);
		cSum.addAll(C);
		cSum.addAll(AB);
		cSum.addAll(AC);
		cSum.addAll(BC);
		cSum.addAll(ABC);

		ArrayList<M_Timetable_ShowTimeRemain_Response> list = new ArrayList<>(cSum);

		Collections.sort(list, Comparator.comparing(M_Timetable_ShowTimeRemain_Response::getId));

		Collection<M_Timetable_ShowTimeRemain_Response> result = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		result.addAll(list);

		return result;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> UnionDayAndTimeForAuto(
			Collection<M_Timetable_ShowTimeRemain_Response> cSum, Collection<M_Timetable_ShowTimeRemain_Response> cA,
			Collection<M_Timetable_ShowTimeRemain_Response> cB, Collection<M_Timetable_ShowTimeRemain_Response> cC) {

		cSum.removeAll(cA);
		cSum.removeAll(cB);
		cSum.removeAll(cC);

		Collection<M_Timetable_ShowTimeRemain_Response> AB = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cA);
		AB.addAll(cB);
		AB.retainAll(cA);
		AB.retainAll(cB);

		Collection<M_Timetable_ShowTimeRemain_Response> BC = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cB);
		BC.addAll(cC);
		BC.retainAll(cB);
		BC.retainAll(cC);

		Collection<M_Timetable_ShowTimeRemain_Response> AC = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cA);
		AC.addAll(cC);
		AC.retainAll(cA);
		AC.retainAll(cC);

		Collection<M_Timetable_ShowTimeRemain_Response> ABC = new TreeSet<M_Timetable_ShowTimeRemain_Response>(AB);
		ABC.addAll(cC);
		ABC.retainAll(cA);
		ABC.retainAll(cB);
		ABC.retainAll(cC);

		AB.removeAll(ABC);
		BC.removeAll(ABC);
		AC.removeAll(ABC);

		Collection<M_Timetable_ShowTimeRemain_Response> A = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cA);
		A.removeAll(AB);
		A.removeAll(AC);
		A.removeAll(ABC);

		Collection<M_Timetable_ShowTimeRemain_Response> B = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cB);
		B.removeAll(AB);
		B.removeAll(BC);
		B.removeAll(ABC);

		Collection<M_Timetable_ShowTimeRemain_Response> C = new TreeSet<M_Timetable_ShowTimeRemain_Response>(cC);
		C.removeAll(AC);
		C.removeAll(BC);
		C.removeAll(ABC);

		for (M_Timetable_ShowTimeRemain_Response ASub : A) {
			ASub.setText("!" + ASub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response BSub : B) {
			BSub.setText("!" + BSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response CSub : C) {
			CSub.setText("!" + CSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response ABSub : AB) {
			ABSub.setText("!" + ABSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response ACSub : AC) {
			ACSub.setText("!" + ACSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response BCSub : BC) {
			BCSub.setText("!" + BCSub.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response ABCSub : ABC) {
			ABCSub.setText("!" + ABCSub.getText());
		}

		cSum.addAll(A);
		cSum.addAll(B);
		cSum.addAll(C);
		cSum.addAll(AB);
		cSum.addAll(AC);
		cSum.addAll(BC);
		cSum.addAll(ABC);

		ArrayList<M_Timetable_ShowTimeRemain_Response> list = new ArrayList<>(cSum);

		Collections.sort(list, Comparator.comparing(M_Timetable_ShowTimeRemain_Response::getId));

		Collection<M_Timetable_ShowTimeRemain_Response> result = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		result.addAll(list);

		return result;
	}

	public Collection<M_For_Selection_Response> UnionRoom(
			Collection<M_For_Selection_Response> cA, Collection<M_For_Selection_Response> cB) {

		Collection<M_For_Selection_Response> result = new ArrayList<M_For_Selection_Response>();

		Collection<M_For_Selection_Response> cD = new ArrayList<M_For_Selection_Response>(cA);

		cA.removeAll(cB);

		cD.removeAll(cA);

		for (M_For_Selection_Response subCB : cD) {
			subCB.setText("🟡 " + subCB.getText());
		}

		cA.addAll(cD);

		ArrayList<M_For_Selection_Response> list = new ArrayList<>(cA);

		Collections.sort(list, Comparator.comparing(M_For_Selection_Response::getId));

		result.addAll(list);

		return result;
	}

	public Collection<M_For_Selection_Response> UnionRoomForAuto(
			Collection<M_For_Selection_Response> cA, Collection<M_For_Selection_Response> cB) {

		Collection<M_For_Selection_Response> result = new ArrayList<M_For_Selection_Response>();

		Collection<M_For_Selection_Response> cD = new ArrayList<M_For_Selection_Response>(cA);

		cA.removeAll(cB);

		cD.removeAll(cA);

		for (M_For_Selection_Response subCB : cD) {
			subCB.setText("!" + subCB.getText());
		}

		cA.addAll(cD);

		ArrayList<M_For_Selection_Response> list = new ArrayList<>(cA);

		Collections.sort(list, Comparator.comparing(M_For_Selection_Response::getId));

		result.addAll(list);

		return result;
	}

	public int deconvertStartTime(String input) {
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

	public String convertStartTime(int input) {
		String output = "08:00:00";
		switch (input) {
			case 1:
				output = "08:00:00";
				break;
			case 2:
				output = "09:00:00";
				break;
			case 3:
				output = "10:00:00";
				break;
			case 4:
				output = "11:00:00";
				break;
			case 5:
				output = "12:00:00";
				break;
			case 6:
				output = "13:00:00";
				break;
			case 7:
				output = "14:00:00";
				break;
			case 8:
				output = "15:00:00";
				break;
			case 9:
				output = "16:00:00";
				break;
			case 10:
				output = "17:00:00";
				break;
			case 11:
				output = "18:00:00";
				break;
			case 12:
				output = "19:00:00";
				break;
			case 13:
				output = "20:00:00";
				break;
			case 14:
				output = "21:00:00";
				break;
		}
		return output;
	}

	public int deconvertEndTime(String input) {
		int output = 0;
		switch (input) {

			case "09:00:00":
				output = 1;
				break;
			case "10:00:00":
				output = 2;
				break;
			case "11:00:00":
				output = 3;
				break;
			case "12:00:00":
				output = 4;
				break;
			case "13:00:00":
				output = 5;
				break;
			case "14:00:00":
				output = 6;
				break;
			case "15:00:00":
				output = 7;
				break;
			case "16:00:00":
				output = 8;
				break;
			case "17:00:00":
				output = 9;
				break;
			case "18:00:00":
				output = 10;
				break;
			case "19:00:00":
				output = 11;
				break;
			case "20:00:00":
				output = 12;
				break;
			case "21:00:00":
				output = 13;
				break;
			case "22:00:00":
				output = 14;
				break;
		}
		return output;
	}

	public String convertEndTime(int input) {
		String output = "XX:XX:XX";
		switch (input) {
			case 1:
				output = "09:00:00";
				break;
			case 2:
				output = "10:00:00";
				break;
			case 3:
				output = "11:00:00";
				break;
			case 4:
				output = "12:00:00";
				break;
			case 5:
				output = "13:00:00";
				break;
			case 6:
				output = "14:00:00";
				break;
			case 7:
				output = "15:00:00";
				break;
			case 8:
				output = "16:00:00";
				break;
			case 9:
				output = "17:00:00";
				break;
			case 10:
				output = "18:00:00";
				break;
			case 11:
				output = "19:00:00";
				break;
			case 12:
				output = "20:00:00";
				break;
			case 13:
				output = "21:00:00";
				break;
			case 14:
				output = "22:00:00";
				break;
		}
		return output;
	}

	public String convertDayOfWeek(int input) {
		String output = "วันใด";
		switch (input) {
			case 1:
				output = "วันจันทร์";
				break;
			case 2:
				output = "วันอังคาร";
				break;
			case 3:
				output = "วันพุธ";
				break;
			case 4:
				output = "วันพฤหัสบดี";
				break;
			case 5:
				output = "วันศุกร์";
				break;
			case 6:
				output = "วันเสาร์";
				break;
			case 7:
				output = "วันอาทิตย์";
				break;
		}
		return output;
	}
}
