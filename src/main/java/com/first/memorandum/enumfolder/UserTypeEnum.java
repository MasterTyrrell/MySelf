package com.first.memorandum.enumfolder;

public enum UserTypeEnum {

    USER(2,"普通用户"),
    ADMIN(1,"管理员");

    private Integer code;
    private String msg;

    UserTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
