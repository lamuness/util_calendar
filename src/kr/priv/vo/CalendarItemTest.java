package kr.priv.vo;

/**
 * Created by Lee Jae Wook on 2020-03-13
 */
public class CalendarItemTest {
    String nm;
    String desc;

    public CalendarItemTest(String nm, String desc) {
        this.nm = nm;
        this.desc = desc;
    }

    public CalendarItemTest() {
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CalendarItemTest{" +
                "nm='" + nm + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
