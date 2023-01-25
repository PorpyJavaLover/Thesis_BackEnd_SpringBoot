package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

	public Iterable<M_Timetable_ShowAllStaff_Response> toMTimetableStaff(Iterable<Timetable> source) {

		if (source == null) {
			return null;
		}

		Collection<M_Timetable_ShowAllStaff_Response> target = new ArrayList<M_Timetable_ShowAllStaff_Response>();

		for (Timetable sourceTmp : source) {
			M_Timetable_ShowAllStaff_Response targetSub = new M_Timetable_ShowAllStaff_Response();
			targetSub.setId(sourceTmp.getTimetableId());
			targetSub.setYears(sourceTmp.getYears().toString());
			targetSub.setSemester(sourceTmp.getSemester().toString());
			targetSub.setCourse_id(sourceTmp.getCourseId().getCourseId().toString());
			targetSub.setCourse_name(sourceTmp.getCourseId().getCourse_title().toString());
			targetSub.setCourse_type(sourceTmp.getCourseType());
			targetSub.setCourse_type_name(sourceTmp.getCourseType() == 0 ? "ทฤษฎี" : "ปฏิบัติ");
			;
			targetSub.setGroup_id(sourceTmp.getGroupId().getGroupId().toString());
			targetSub.setGroup_name(sourceTmp.getGroupId().getGroup_name().toString());
			targetSub.setMember_Id(sourceTmp.getMemberId().getMemberId());
			targetSub.setMember_name(sourceTmp.getMemberId().getTitleId().getTitleShort().toString() + " "
					+ sourceTmp.getMemberId().getThFirstName().toString() + " "
					+ sourceTmp.getMemberId().getThLastName().toString());
			targetSub.setDay_of_week(sourceTmp.getDayOfWeek() == null ? null : sourceTmp.getDayOfWeek());
			targetSub.setStart_time(sourceTmp.getStartTime() == null ? null : sourceTmp.getStartTime());
			targetSub.setEnd_time(sourceTmp.getEndTime() == null ? null : sourceTmp.getEndTime());
			targetSub.setRoom_id(sourceTmp.getRoomId() == null ? null : sourceTmp.getRoomId().getRoomId());
			targetSub.setRoom_name(sourceTmp.getRoomId() == null ? null : sourceTmp.getRoomId().getName());
			targetSub.setTime_locker(sourceTmp.isTimeLocker());
			targetSub.setRoom_locker(sourceTmp.isRoomLocker());
			target.add(targetSub);
		}

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

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeStartOptionStaff(Iterable<Timetable> sourceA,
			Collection<NotTeach> sourceC, Timetable sourceD , Iterable<Timetable> sourceE) {

		if (sourceA == null) {
			return null;
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (Timetable sourceTmp : sourceA) {

			int interim = deconvertStartTime(sourceTmp.getEndTime().toString())
					- deconvertStartTime(sourceTmp.getStartTime().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubA = new M_Timetable_ShowTimeRemain_Response();
				targetSubA.setId(deconvertStartTime(sourceTmp.getStartTime().toString()) + (interim - 1 - i));
				targetSubA.setValue(
						convertStartTime(deconvertStartTime(sourceTmp.getStartTime().toString()) + (interim - 1 - i)));
				targetSubA.setText(
						convertStartTime(deconvertStartTime(sourceTmp.getStartTime().toString()) + (interim - 1 - i)));
				targetA.add(targetSubA);
			}
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetE = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (NotTeach sourceTmp : sourceC) {

			int interim = deconvertStartTime(sourceTmp.getTimeEnd().toString())
					- deconvertStartTime(sourceTmp.getTimeStart().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubE = new M_Timetable_ShowTimeRemain_Response();
				targetSubE.setId(deconvertStartTime(sourceTmp.getTimeStart().toString()) + (interim - 1 - i));
				targetSubE.setValue(
						convertStartTime(deconvertStartTime(sourceTmp.getTimeStart().toString()) + (interim - 1 - i)));
				targetSubE.setText(
						convertStartTime(deconvertStartTime(sourceTmp.getTimeStart().toString()) + (interim - 1 - i)));
				targetE.add(targetSubE);
			}
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetB = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		for (int i = 1; i <= 14; i++) {
			M_Timetable_ShowTimeRemain_Response targetSubB = new M_Timetable_ShowTimeRemain_Response();
			targetSubB.setId(i);
			targetSubB.setValue(convertStartTime(i));
			targetSubB.setText(convertStartTime(i));
			targetB.add(targetSubB);
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetF = relativeComplementBInABussy(targetB, targetA,
				targetE);

		Collection<M_Timetable_ShowTimeRemain_Response> targetD = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		targetD = targetF;

		int x;

		if (sourceD.getCourseType() == 0) {
			x = sourceD.getCourseId().getCourseLect();
		} else {
			x = sourceD.getCourseId().getCoursePerf();
		}

		List<M_Timetable_ShowTimeRemain_Response> subList = ((ArrayList<M_Timetable_ShowTimeRemain_Response>) targetD)
				.subList(0, targetD.size() - (x));
		Collection<M_Timetable_ShowTimeRemain_Response> targetG = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		targetG.addAll(targetD);
		targetD.removeAll(subList);
		targetG.removeAll(targetD);

		return targetG;

	}

	public Iterable<M_Timetable_ShowTimeRemain_Response> toMTimeEndOptionStaff(Iterable<Timetable> source,
			Collection<NotTeach> sourceC, Timetable sourceD) {

		if (source == null) {
			return null;
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetA = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (Timetable sourceTmp : source) {

			int interim = deconvertEndTime(sourceTmp.getEndTime().toString())
					- deconvertEndTime(sourceTmp.getStartTime().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubA = new M_Timetable_ShowTimeRemain_Response();
				targetSubA.setId(deconvertEndTime(sourceTmp.getStartTime().toString()) + (interim - i));
				targetSubA.setValue(
						convertEndTime(deconvertEndTime(sourceTmp.getStartTime().toString()) + (interim - i)));
				targetSubA
						.setText(convertEndTime(deconvertEndTime(sourceTmp.getStartTime().toString()) + (interim - i)));
				targetA.add(targetSubA);
			}
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetE = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (NotTeach sourceTmp : sourceC) {

			int interim = deconvertEndTime(sourceTmp.getTimeEnd().toString())
					- deconvertEndTime(sourceTmp.getTimeStart().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubE = new M_Timetable_ShowTimeRemain_Response();
				targetSubE.setId(deconvertEndTime(sourceTmp.getTimeStart().toString()) + (interim - i));
				targetSubE.setValue(
						convertEndTime(deconvertEndTime(sourceTmp.getTimeStart().toString()) + (interim - i)));
				targetSubE
						.setText(convertEndTime(deconvertEndTime(sourceTmp.getTimeStart().toString()) + (interim - i)));
				targetE.add(targetSubE);
			}
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetB = new ArrayList<M_Timetable_ShowTimeRemain_Response>();
		for (int i = 1; i <= 14; i++) {
			M_Timetable_ShowTimeRemain_Response targetSubC = new M_Timetable_ShowTimeRemain_Response();
			targetSubC.setId(i);
			targetSubC.setValue(convertEndTime(i));
			targetSubC.setText(convertEndTime(i));
			targetB.add(targetSubC);
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetF = relativeComplementBInABussy(targetB, targetA,
				targetE);

		Collection<M_Timetable_ShowTimeRemain_Response> targetD = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		targetD = targetF;

		int x;

		if (sourceD.getCourseType() == 0) {
			x = sourceD.getCourseId().getCourseLect();
		} else {
			x = sourceD.getCourseId().getCoursePerf();
		}

		List<M_Timetable_ShowTimeRemain_Response> subList = ((ArrayList<M_Timetable_ShowTimeRemain_Response>) targetD)
				.subList(x, targetD.size());
		Collection<M_Timetable_ShowTimeRemain_Response> targetG = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		targetG.addAll(targetD);
		targetD.removeAll(subList);
		targetG.removeAll(targetD);

		return targetG;
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
			x = sourceA.getCourseId().getCoursePerf();
		}

		y = deconvertEndTime(Endtime);
		z = y - x;

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
			x = sourceA.getCourseId().getCoursePerf();
		}

		y = deconvertStartTime(startTime);
		z = x + y;

		targetA.setId(z);
		targetA.setValue(convertEndTime(z));
		targetA.setText(convertEndTime(z));

		return targetA;
	}

	public Collection<M_For_Selection_Response> toMRoomStaff(Iterable<Timetable> sourceD, Iterable<Room> sourceA) {

		if (sourceD == null) {
			return null;
		}

		Collection<M_For_Selection_Response> targetD = new ArrayList<M_For_Selection_Response>();

		for (Timetable sourceDTmp : sourceD) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceDTmp.getRoomId().getRoomId());
			targetSub.setValue(sourceDTmp.getRoomId().getRoomId().toString());
			targetSub.setText(sourceDTmp.getRoomId().getName());
			targetD.add(targetSub);
		}

		Collection<M_For_Selection_Response> targetA = new ArrayList<M_For_Selection_Response>();

		for (Room sourceATmp : sourceA) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceATmp.getRoomId());
			targetSub.setValue(sourceATmp.getRoomId().toString());
			targetSub.setText(sourceATmp.getName());
			targetA.add(targetSub);
		}

		Collection<M_For_Selection_Response> targetE = relaComplementBInA(targetA, targetD);

		return targetE;
	}

	public Collection<M_For_Selection_Response> toMRoomStaffAuto(Iterable<Timetable> sourceD, Iterable<Room> sourceA) {

		if (sourceD == null) {
			return null;
		}

		Collection<M_For_Selection_Response> targetD = new ArrayList<M_For_Selection_Response>();

		for (Timetable sourceDTmp : sourceD) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceDTmp.getRoomId().getRoomId());
			targetSub.setValue(sourceDTmp.getRoomId().getRoomId().toString());
			targetSub.setText(sourceDTmp.getRoomId().getName());
			targetD.add(targetSub);
		}

		Collection<M_For_Selection_Response> targetA = new ArrayList<M_For_Selection_Response>();

		for (Room sourceATmp : sourceA) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceATmp.getRoomId());
			targetSub.setValue(sourceATmp.getRoomId().toString());
			targetSub.setText(sourceATmp.getName());
			targetA.add(targetSub);
		}

		Collection<M_For_Selection_Response> targetE = relaComplementBInAAuto(targetA, targetD);

		return targetE;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> relativeComplementBInAWarring(
			Collection<M_Timetable_ShowTimeRemain_Response> cA, Collection<M_Timetable_ShowTimeRemain_Response> cB) {

		Collection<M_Timetable_ShowTimeRemain_Response> result = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		Collection<M_Timetable_ShowTimeRemain_Response> cD = new ArrayList<M_Timetable_ShowTimeRemain_Response>(cA);

		cA.removeAll(cB);

		cD.removeAll(cA);

		for (M_Timetable_ShowTimeRemain_Response subCB : cD) {
			subCB.setText("⚠️" + subCB.getText());
		}

		cA.addAll(cD);

		ArrayList<M_Timetable_ShowTimeRemain_Response> list = new ArrayList<>(cA);

		Collections.sort(list, Comparator.comparing(M_Timetable_ShowTimeRemain_Response::getId));

		result.addAll(list);

		return result;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> relativeComplementBInABussy(
			Collection<M_Timetable_ShowTimeRemain_Response> cA, Collection<M_Timetable_ShowTimeRemain_Response> cB,
			Collection<M_Timetable_ShowTimeRemain_Response> cC) {

		Collection<M_Timetable_ShowTimeRemain_Response> result = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		Collection<M_Timetable_ShowTimeRemain_Response> cD = new ArrayList<M_Timetable_ShowTimeRemain_Response>(cA);

		Collection<M_Timetable_ShowTimeRemain_Response> cE = new ArrayList<M_Timetable_ShowTimeRemain_Response>(cC);

		Collection<M_Timetable_ShowTimeRemain_Response> cG = new ArrayList<M_Timetable_ShowTimeRemain_Response>(cC);

		cA.removeAll(cB);

		cD.removeAll(cA);

		cA.removeAll(cC);

		cE.removeAll(cA);

		Collection<M_Timetable_ShowTimeRemain_Response> cF = new ArrayList<M_Timetable_ShowTimeRemain_Response>(cD);

		cD.removeAll(cE);

		cF.removeAll(cD);

		cG.removeAll(cF);

		for (M_Timetable_ShowTimeRemain_Response subCB : cD) {
			subCB.setText("⚠️" + subCB.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response subCB : cF) {
			subCB.setText("⚠️📌" + subCB.getText());
		}

		for (M_Timetable_ShowTimeRemain_Response subCB : cG) {
			subCB.setText("📌" + subCB.getText());
		}

		cF.addAll(cG);
		cD.addAll(cF);
		cA.addAll(cD);

		ArrayList<M_Timetable_ShowTimeRemain_Response> list = new ArrayList<>(cA);

		Collections.sort(list, Comparator.comparing(M_Timetable_ShowTimeRemain_Response::getId));

		result.addAll(list);

		return result;
	}

	public Collection<M_For_Selection_Response> relaComplementBInA(
			Collection<M_For_Selection_Response> cA, Collection<M_For_Selection_Response> cB) {

		Collection<M_For_Selection_Response> result = new ArrayList<M_For_Selection_Response>();

		Collection<M_For_Selection_Response> cD = new ArrayList<M_For_Selection_Response>(cA);

		cA.removeAll(cB);

		cD.removeAll(cA);

		for (M_For_Selection_Response subCB : cD) {
			subCB.setText("⚠️" + subCB.getText());
		}

		cA.addAll(cD);

		ArrayList<M_For_Selection_Response> list = new ArrayList<>(cA);

		Collections.sort(list, Comparator.comparing(M_For_Selection_Response::getId));

		result.addAll(list);

		return result;
	}

	public Collection<M_For_Selection_Response> relaComplementBInAAuto(
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
}
