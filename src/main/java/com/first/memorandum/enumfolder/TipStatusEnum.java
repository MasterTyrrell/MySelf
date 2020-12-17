package com.first.memorandum.enumfolder;

public enum TipStatusEnum {

    START(1,"开启"),
    CLOSE(0,"关闭");

    private Integer code;
    private String desc;

    TipStatusEnum(Integer code, String desc) {
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
        for(TipStatusEnum enums:TipStatusEnum.values()){
            if(enums.getCode()==code){
                return true;
            }
        }
        return false;
    }
}
