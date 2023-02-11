package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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


	

	public Collection<M_Timetable_ShowTimeRemain_Response> toMTimeStartOptionStaff(boolean mode,
			Iterable<Timetable> sourceA, Collection<NotTeach> sourceB, Timetable sourceC, Collection<Timetable> sourceD) {

		if (sourceA == null) {
			return null;
		}

		Collection<M_Timetable_ShowTimeRemain_Response> rawBase = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (int i = 1; i <= 14; i++) {
			M_Timetable_ShowTimeRemain_Response targetSubBase = new M_Timetable_ShowTimeRemain_Response();
			targetSubBase.setId(i);
			targetSubBase.setValue(convertStartTime(i));
			targetSubBase.setText(convertStartTime(i));
			rawBase.add(targetSubBase);
		}

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

		Collection<M_Timetable_ShowTimeRemain_Response> targetInconvenient = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (NotTeach sourceTmp : sourceB) {

			int interim = deconvertStartTime(sourceTmp.getTimeEnd().toString())
					- deconvertStartTime(sourceTmp.getTimeStart().toString());

			for (int i = 0; i < interim; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubE = new M_Timetable_ShowTimeRemain_Response();
				targetSubE.setId(deconvertStartTime(sourceTmp.getTimeStart().toString()) + (interim - 1 - i));
				targetSubE.setValue(
						convertStartTime(deconvertStartTime(sourceTmp.getTimeStart().toString()) + (interim - 1 - i)));
				targetSubE.setText(
						convertStartTime(deconvertStartTime(sourceTmp.getTimeStart().toString()) + (interim - 1 - i)));
				targetInconvenient.add(targetSubE);
			}
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetH = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (Timetable sourceSubE : sourceD) {
			Integer j = 0;
			if (sourceSubE.getCourseType() == 0) {
				j = sourceSubE.getCourseId().getCourseLect();
			} else if (sourceSubE.getCourseType() == 1) {
				j = sourceSubE.getCourseId().getCoursePerf();
			}
			for (int i = 0; i < j + 1; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubH = new M_Timetable_ShowTimeRemain_Response();
				targetSubH.setId(deconvertStartTime(sourceSubE.getStartTime().toString()) + i);
				targetSubH.setValue(convertStartTime(deconvertStartTime(sourceSubE.getStartTime().toString()) + i));
				targetSubH.setText(convertStartTime(deconvertStartTime(sourceSubE.getStartTime().toString()) + i));
				targetH.add(targetSubH);
			}
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetD;

		if (mode) {
			targetD = DayAndTimeComplement(rawBase, targetA, targetInconvenient, targetH);
		} else {
			targetD = DayAndTimeComplementForAuto(rawBase, targetA, targetInconvenient, targetH);
		}

		int x;

		if (sourceC.getCourseType() == 0) {
			x = sourceC.getCourseId().getCourseLect();
		} else {
			x = sourceC.getCourseId().getCoursePerf();
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
			Collection<NotTeach> sourceC, Timetable sourceD, Collection<Timetable> sourceE) {

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
			M_Timetable_ShowTimeRemain_Response targetSubB = new M_Timetable_ShowTimeRemain_Response();
			targetSubB.setId(i);
			targetSubB.setValue(convertEndTime(i));
			targetSubB.setText(convertEndTime(i));
			targetB.add(targetSubB);
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetH = new ArrayList<M_Timetable_ShowTimeRemain_Response>();

		for (Timetable sourceSubE : sourceE) {
			Integer j = 0;
			if (sourceSubE.getCourseType() == 0) {
				j = sourceSubE.getCourseId().getCourseLect();
			} else if (sourceSubE.getCourseType() == 1) {
				j = sourceSubE.getCourseId().getCoursePerf();
			}
			for (int i = 0; i < j + 1; i++) {
				M_Timetable_ShowTimeRemain_Response targetSubH = new M_Timetable_ShowTimeRemain_Response();
				targetSubH.setId(deconvertEndTime(sourceSubE.getEndTime().toString()) - i);
				targetSubH.setValue(convertEndTime(deconvertEndTime(sourceSubE.getEndTime().toString()) - i));
				targetSubH.setText(convertEndTime(deconvertEndTime(sourceSubE.getEndTime().toString()) - i));
				targetH.add(targetSubH);
			}
		}

		Collection<M_Timetable_ShowTimeRemain_Response> targetF = DayAndTimeComplement(targetB, targetA,
				targetE, targetH);

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

	public Collection<M_For_Selection_Response> toMRoomStaff(boolean mode, Iterable<Timetable> sourceD,
			Iterable<Room> sourceA) {

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

		Collection<M_For_Selection_Response> targetE;

		if (mode) {
			targetE = RoomComplement(targetA, targetD);
		} else {
			targetE = RoomComplementForAuto(targetA, targetD);
			;
		}

		return targetE;
	}

	public Collection<M_Timetable_ShowTimeRemain_Response> DayAndTimeComplement(
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

	public Collection<M_Timetable_ShowTimeRemain_Response> DayAndTimeComplementForAuto(
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

	public Collection<M_For_Selection_Response> RoomComplement(
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

	public Collection<M_For_Selection_Response> RoomComplementForAuto(
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
