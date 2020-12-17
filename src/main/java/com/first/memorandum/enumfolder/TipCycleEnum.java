package com.first.memorandum.enumfolder;

public enum TipCycleEnum{

    O(0,"提醒一次关闭状态"),
    DAY(1,"日"),
    WEEK(2,"周"),
    MONTH(3,"月"),
    YEAR(4,"年")
    ;

    private Integer code;
    private String desc;

    TipCycleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Boolean checkCodeExist(Integer code){
        for(TipCycleEnum enums:TipCycleEnum.values()){
            if(enums.getCode()==code){
                return true;
            }
        }
        return false;
    }
}
