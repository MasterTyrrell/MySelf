package com.first.memorandum.enumfolder;

public enum ResponseEnum {

    SUCCESS("200","成功"),
    FAILED("-1","失败"),
    NO_MOBILENO_PASSWORD("-2","手机号或密码不能为空"),
    NO_USER("-3","该手机号还未注册"),
    HAVING_USER("-4","该手机号已注册"),
    NO_AUTH_CODE("-5","授权码或手机号不能为空"),
    NO_MOBILENO_AUTHCODE_VERIFYCODE("-6","手机号或授权码或验证码为空"),
    VERIFYCODE_ERROR("-7","验证码错误"),
    VERIFYCODE_SEND_THAN("-8","验证码服务暂时停用"),
    LOGIN_TIMEOUT("-9","登录超时"),
    ERROR_PASSWORD("-10","密码不正确"),
    ERROR_USER("-11","该用户暂时无法使用"),
    NO_EMAIL("-12","邮件不能为空"),
    NO_NOTE_PARAMETER("-13","缺少笔记必要参数"),
    NO_NOTE_NO("-13","缺少笔记编号"),
    NO_PAHE_PARAMETER("-14","没有分页参数"),
    EEROR_PAHE_PARAMETER("-15","分页参数不应该小于1"),
    NO_TIP_PARAMETER("-15","缺少提醒的重要参数"),
    ERROR_STATUS("-16","状态码不对"),
    ERROR_CYCLE("-17","日期周期不对")

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
