package com.thesis.scheduling.modellevel.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.thesis.scheduling.modellevel.entity.CpeRoom;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;

@Component
public class RoomMapper {

	public Iterable<M_For_Selection_Response> toMShowAllForSelection(Iterable<CpeRoom> sourceA ) {

		if (sourceA == null) {
			return null;
		}

		Collection<M_For_Selection_Response> target = new ArrayList<M_For_Selection_Response>();
		
		for (CpeRoom sourceATmp : sourceA) {
			M_For_Selection_Response targetSub = new M_For_Selection_Response();
			targetSub.setId(sourceATmp.getRoomId());
			targetSub.setValue(sourceATmp.getRoomId().toString());
			targetSub.setText(sourceATmp.getName());
			target.add(targetSub);
		}
		
		return target;
		
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
