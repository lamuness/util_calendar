package kr.priv.vo;

public enum CommCalNotPosReason {
    NOT_REG("등록불가"), FULL_REG("전부등록됨"), NOT_NOW("등록대기"), NOT_PROC("운영안함"), PASS_DAY("지난간일자");

    String strReason;

    CommCalNotPosReason(String strReason) {
        this.strReason = strReason;
    }

    public String getStrReason() {
        return strReason;
    }

    public void setStrReason(String strReason) {
        this.strReason = strReason;
    }
}
