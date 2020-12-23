package com.first.memorandum.enumfolder;

public enum SmsResponseEnum {

    SUCCESS("1","发送成功"),
    FU_ONE("-1","没有该用户账户"),
    FU_TWO("-2","接口密钥不正确\n" +
            "不是账户登陆密码"),
    FU_TWOONE("-21","MD5接口密钥加密不正确"),
    FU_THREE("-3","短信数量不足"),
    FU_ONEONE("-11","该用户被禁用"),
    FU_ONEFOUR("-14","短信内容出现非法字符"),
    FU_FOUR("-4","手机号格式不正确"),
    FU_FOURONE("-41","手机号码为空"),
    FU_FOURTWO("-42","短信内容为空"),
    FU_FIVEONE("-51","短信签名格式不正确"),
    FU_FIVETWO("-52","短信签名太长\n" +
            "建议签名10个字符以内"),
    FU_SIX("-6","IP限制")
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
