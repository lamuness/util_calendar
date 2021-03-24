package kr.priv;

import kr.priv.vo.CalendarItemTest;
import kr.priv.vo.CommCalendar;
import kr.priv.vo.CommCalendarDay;

import java.util.ArrayList;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        int year = 2021;
        int month = 3;

        //달력생성
        CommCalendar commCalendar = CalendarUtils.makeCalendar(year, month, new Function<CommCalendarDay, CommCalendarDay>() {
            public CommCalendarDay apply(CommCalendarDay commCalendarDay) {
                //1일이 생길때마다 체크해야하는 항목이 필요할경우 여기서 수행
                if ("12".equals(commCalendarDay.getMonth()) && "31".equals(commCalendarDay.getDate())) {
                    commCalendarDay.setHoliday(true);
                    commCalendarDay.setHolidayName("년에 마지막날 쉼(개인)");
                }
                return commCalendarDay;
            }
        });

        //만들어진 달력에서, 특정 일자만 불러와 편집하고 싶을때 예제
        CommCalendarDay commCalendarDay = commCalendar.getMapCommCalendarDay().get("20210301");

        if (commCalendarDay != null) {
            commCalendarDay.setHolidayName("새해첫날");
            commCalendarDay.setHoliday(true);

            ArrayList arrCalendarItemTest = new ArrayList<CalendarItemTest>();
            arrCalendarItemTest.add(new CalendarItemTest("달리기", "첫날부터 열심히 달릴꺼임"));
            arrCalendarItemTest.add(new CalendarItemTest("운동", "2시간씩 할꺼임"));

            commCalendarDay.setLst(arrCalendarItemTest);
        }

        //완성된 결과물 text로 보기
        for (int i = 0; i < commCalendar.getCommCalendarDays().length; i++) {
            System.out.println("");
            System.out.println("===============================================================================================================");
            for (int j = 0; j < commCalendar.getCommCalendarDays()[i].length; j++) {
                CommCalendarDay item = commCalendar.getCommCalendarDays()[i][j];
                System.out.print(item.getYear() + "-" + item.getMonth() + "-" + item.getDate() + " | ");
            }
        }
        System.out.println("");
        System.out.println("===============================================================================================================");
        for (int i = 0; i < commCalendar.getCommCalendarDays().length; i++) {
            for (int j = 0; j < commCalendar.getCommCalendarDays()[i].length; j++) {
                CommCalendarDay item = commCalendar.getCommCalendarDays()[i][j];
                System.out.println(item.toString());
            }
        }
    }
}
