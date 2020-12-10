package com.first.memorandum.enumfolder;

public enum ResponseEnum {

    SUCCESS("200","成功"),
    FAILED("-1","失败"),
    NO_MOBILENO_PASSWORD("-2","手机号或密码不能为空"),
    NO_USER("-3","该手机号还未注册"),
    HAVING_USER("-4","该手机号已注册"),
    NO_AUTH_CODE("-5","授权码不能为空"),
    NO_MOBILENO_AUTHCODE_VERIFYCODE("-6","手机号或授权码或验证码为空"),
    VERIFYCODE_ERROR("-7","验证码错误"),
    VERIFYCODE_SEND_THAN("-8","验证码服务暂时停用"),
    LOGIN_TIMEOUT("-9","登录超时"),
    ERROR_PASSWORD("-10","密码不正确"),
    ERROR_USER("-11","该用户暂时无法使用");
    ;

    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
