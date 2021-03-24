package kr.priv.vo;


import java.util.ArrayList;

public class CommCalendarDay {

	protected String year;
	protected String month;
	protected String date;
	protected String weekWord;
	protected boolean weekend;
	protected boolean holiday;
	protected boolean calledMonth;
	protected String holidayName;
	boolean possibleEnr = true;
	CommCalNotPosReason commCalNotPosReason = CommCalNotPosReason.POS_REG;
	protected Float reservedCnt;
	protected ArrayList<Object> lst = new ArrayList<Object>();

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeekWord() {
		return weekWord;
	}

	public void setWeekWord(String weekWord) {
		this.weekWord = weekWord;
	}

	public boolean isWeekend() {
		return weekend;
	}

	public void setWeekend(boolean weekend) {
		this.weekend = weekend;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	public boolean isCalledMonth() {
		return calledMonth;
	}

	public void setCalledMonth(boolean calledMonth) {
		this.calledMonth = calledMonth;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public boolean isPossibleEnr() {
		return possibleEnr;
	}

	public void setPossibleEnr(boolean possibleEnr) {
		this.possibleEnr = possibleEnr;
	}

	public CommCalNotPosReason getCommCalNotPosReason() {
		return commCalNotPosReason;
	}

	public void setCommCalNotPosReason(CommCalNotPosReason commCalNotPosReason) {
		this.commCalNotPosReason = commCalNotPosReason;
	}

	public Float getReservedCnt() {
		return reservedCnt;
	}

	public void setReservedCnt(Float reservedCnt) {
		this.reservedCnt = reservedCnt;
	}

	public ArrayList<Object> getLst() {
		return lst;
	}

	public void setLst(ArrayList<Object> lst) {
		this.lst = lst;
	}

	@Override
	public String toString() {
		return "CommCalendarDay{" +
				"year='" + year + '\'' +
				", month='" + month + '\'' +
				", date='" + date + '\'' +
				", weekWord='" + weekWord + '\'' +
				", weekend=" + weekend +
				", holiday=" + holiday +
				", calledMonth=" + calledMonth +
				", holidayName='" + holidayName + '\'' +
				", possibleEnr=" + possibleEnr +
				", commCalNotPosReason=" + commCalNotPosReason +
				", reservedCnt=" + reservedCnt +
				", lst=" + lst +
				'}';
	}
}
