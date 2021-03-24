package kr.priv.vo;

import java.util.HashMap;

public class CommCalendar {
	HashMap<String, CommCalendarDay> mapCommCalendarDay = new HashMap<String, CommCalendarDay>();

	CommCalendarDay[][] commCalendarDays = new CommCalendarDay[6][7];

	public CommCalendarDay[][] getCommCalendarDays() {
		return commCalendarDays;
	}

	public void setCommCalendarDays(CommCalendarDay[][] commCalendarDays) {
		this.commCalendarDays = commCalendarDays;
	}

	public HashMap<String, CommCalendarDay> getMapCommCalendarDay() {
		return mapCommCalendarDay;
	}

	public void setMapCommCalendarDay(HashMap<String, CommCalendarDay> mapCommCalendarDay) {
		this.mapCommCalendarDay = mapCommCalendarDay;
	}
}
