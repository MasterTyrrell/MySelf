package com.first.memorandum.enumfolder;

public enum SmsResponseEnum {

    SUCCESS("1","发送成功"),
    FU_ONE("-1","没有该用户账户"),
    FU_TWO("-2","没有该用户账户"),
    FU_TWOONE("-21","没有该用户账户"),
    FU_THREE("-3","没有该用户账户"),
    FU_ONEONE("-11","没有该用户账户"),
    FU_ONEFOUR("-14","没有该用户账户"),
    FU_FOUR("-4","没有该用户账户"),
    FU_FOURONE("-41","没有该用户账户"),
    FU_FOURTWO("-42","没有该用户账户"),
    FU_FIVEONE("-51","没有该用户账户"),
    FU_FIVETWO("-52","没有该用户账户"),
    FU_SIX("-6","没有该用户账户")
    ;

    private String code;
    private String dsec;

    SmsResponseEnum(String code, String dsec) {
        this.code = code;
        this.dsec = dsec;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDsec() {
        return dsec;
    }

    public void setDsec(String dsec) {
        this.dsec = dsec;
    }

    public static String getDscByCode(String code){
        for(SmsResponseEnum smsEnum: SmsResponseEnum.values()){
            if(smsEnum.getCode().equals(code)){
                return smsEnum.getDsec();
            }
        }
        return "";
    }
}
