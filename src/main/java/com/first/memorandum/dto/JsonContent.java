package com.first.memorandum.dto;

import com.first.memorandum.enumfolder.ResponseEnum;

public class JsonContent {

    private String code;

    private String msg;

    private Object result;

    public JsonContent(String code,String msg,String result){
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public JsonContent(ResponseEnum response){
        this.code = response.getCode();
        this.msg = response.getMsg();
    }

    public JsonContent(ResponseEnum response,Object object){
        this.code = response.getCode();
        this.msg = response.getMsg();
        this.result = object;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
