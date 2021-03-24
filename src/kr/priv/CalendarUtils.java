package kr.priv;

import kr.priv.vo.CalendarFirstEndDay;
import kr.priv.vo.CalendarFirstEndDayWithBeforeAfter;
import kr.priv.vo.CommCalendar;
import kr.priv.vo.CommCalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Function;

public class CalendarUtils {
    final static Integer[] lastDayOfMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static CalendarFirstEndDay getFirstDayAndEndDay(int year, int month) {
        CalendarFirstEndDay calendarFirstEndDay = new CalendarFirstEndDay();

        calendarFirstEndDay.setYear(year);
        calendarFirstEndDay.setMonth(month);

        int first = weekCalcNum(year);

        int totalDays = 1;
        for (int i = 1; i < month; i++) {
            totalDays += getLastDay(year, i);
        }
        first = (first + totalDays) % 7;

        int endday = getLastDay(year, month);

        calendarFirstEndDay.setFirstDay(first);
        calendarFirstEndDay.setEndDay(endday);

        return calendarFirstEndDay;
    }

    public static CalendarFirstEndDayWithBeforeAfter getFirstDayAndEndDayWithBeforeAfter(int year, int month) {
        CalendarFirstEndDayWithBeforeAfter calendarFirstEndDayWithBeforeAfter = new CalendarFirstEndDayWithBeforeAfter();

        calendarFirstEndDayWithBeforeAfter.setNow(getFirstDayAndEndDay(year, month));

        Calendar calendarBefore = Calendar.getInstance();
        calendarBefore.set(year, month - 1, 1);
        calendarBefore.add(Calendar.MONTH, -1);
        calendarFirstEndDayWithBeforeAfter.setBefore(getFirstDayAndEndDay(calendarBefore.get(Calendar.YEAR), calendarBefore.get(Calendar.MONTH) + 1));

        Calendar calendarAfter = Calendar.getInstance();
        calendarAfter.set(year, month - 1, 1);
        calendarAfter.add(Calendar.MONTH, 1);
        calendarFirstEndDayWithBeforeAfter.setAfter(getFirstDayAndEndDay(calendarAfter.get(Calendar.YEAR), calendarAfter.get(Calendar.MONTH) + 1));

        return calendarFirstEndDayWithBeforeAfter;
    }


    private static int getLastDay(int year, int month) {
        int ret = 0;

        if (month == 2) {
            ret = yearcalc(year) ? 29 : 28;
        } else {
            ret = lastDayOfMonth[month - 1];
        }
        return ret;
    }

    private static int weekCalcNum(int year) {
        year--;
        return (year * 365 + year / 4 - year / 100 + year / 400) % 7;
    }

    /**
     * 윤년체크
     *
     * @param year
     * @return
     */
    private static boolean yearcalc(int year) {
        if (year % 4 == 0 && (!(year % 100 == 0) && !(year % 400 == 0))) {
            return true;
        }
        return false;
    }

    public static CommCalendar makeCalendar(int year, int month, Function<CommCalendarDay, CommCalendarDay> calFunctionCompleteCalendarDay) {
        CalendarFirstEndDayWithBeforeAfter calendarFirstEndDayWithBeforeAfter = getFirstDayAndEndDayWithBeforeAfter(year, month);

        int count = 1;
        int beforeDayCount = calendarFirstEndDayWithBeforeAfter.getBefore().getEndDay();
        int afterDayCount = 1;

        CommCalendar commCalendar = new CommCalendar();
        CommCalendarDay[][] calDays = commCalendar.getCommCalendarDays();

        for (int i = 0; i < calDays.length; i++) { // 6번 돌림 (월의 라인)
            if (calendarFirstEndDayWithBeforeAfter.getNow().getFirstDay() == 0 && i == 0) {
                //firstDay 가 0이면 상단 한라인에, 이전 달이 전부 들어가야함
                for (int j = 0; j < calDays[i].length; j++) {
                    calDays[i][j] = getCalendarItem(calendarFirstEndDayWithBeforeAfter.getBefore().getYear(), calendarFirstEndDayWithBeforeAfter.getBefore().getMonth() - 1,
                            beforeDayCount - (calDays[i].length - j - 1), false, calFunctionCompleteCalendarDay);
                    commCalendar = setDimesionInfoToHashMap(commCalendar, i, j, calDays[i][j]);
                }
                continue;
            }
            for (int j = 0; j < calDays[i].length; j++) { // 7번 돌림 (요일)
                if (i == 0) {
                    if (j < calendarFirstEndDayWithBeforeAfter.getNow().getFirstDay()) {
                        //이전달 넣기
                        calDays[i][j] = getCalendarItem(calendarFirstEndDayWithBeforeAfter.getBefore().getYear(), calendarFirstEndDayWithBeforeAfter.getBefore().getMonth() - 1,
                                beforeDayCount - (calendarFirstEndDayWithBeforeAfter.getNow().getFirstDay() - j - 1), false, calFunctionCompleteCalendarDay);
                        commCalendar = setDimesionInfoToHashMap(commCalendar, i, j, calDays[i][j]);

                    } else {
                        calDays[i][j] = getCalendarItem(year, month - 1, count++, true, calFunctionCompleteCalendarDay);
                        commCalendar = setDimesionInfoToHashMap(commCalendar, i, j, calDays[i][j]);
                    }
                } else {
                    if (count > calendarFirstEndDayWithBeforeAfter.getNow().getEndDay()) {
                        calDays[i][j] = getCalendarItem(calendarFirstEndDayWithBeforeAfter.getAfter().getYear(), calendarFirstEndDayWithBeforeAfter.getAfter().getMonth() - 1,
                                afterDayCount++, false, calFunctionCompleteCalendarDay);
                        commCalendar = setDimesionInfoToHashMap(commCalendar, i, j, calDays[i][j]);
                    } else {
                        calDays[i][j] = getCalendarItem(year, month - 1, count++, true, calFunctionCompleteCalendarDay);
                        commCalendar = setDimesionInfoToHashMap(commCalendar, i, j, calDays[i][j]);
                    }

                }
            }
        }
        return commCalendar;
    }

    private static CommCalendar setDimesionInfoToHashMap(CommCalendar commCalendar, int firstDimension, int secondDimension, CommCalendarDay commCalendarDay) {
        commCalendar.getMapCommCalendarDay().put(commCalendarDay.getYear() + commCalendarDay.getMonth() + commCalendarDay.getDate(), commCalendarDay);
        return commCalendar;
    }

    private static CommCalendarDay getCalendarItem(int year, int month, int date, boolean calledMonth, Function<CommCalendarDay, CommCalendarDay> calFunctionCompleteCalendarDay) {
        CommCalendarDay commCalendarDay = new CommCalendarDay();
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        calendar.set(year, month, date);
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyyMMdd");
        String ymd = formatYear.format(calendar.getTime());
        commCalendarDay.setYear(ymd.substring(0, 4));
        commCalendarDay.setMonth(ymd.substring(4, 6));
        commCalendarDay.setDate(ymd.substring(6, 8));
        commCalendarDay.setCalledMonth(calledMonth);

        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {
            commCalendarDay.setWeekend(true);
        } else {
            commCalendarDay.setWeekend(false);
        }
        SimpleDateFormat formatWeek = new SimpleDateFormat("EEEE");
        String weekWord = formatWeek.format(calendar.getTime());
        commCalendarDay.setWeekWord(weekWord);

        commCalendarDay = calFunctionCompleteCalendarDay.apply(commCalendarDay);

        return commCalendarDay;
    }
}
