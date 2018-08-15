package com.oocl.business.model;

import java.util.Map;

public class RespondResult {

    private int statusCode;

    private String msg;

    private Map<String, Object> datas;

    public RespondResult(){

    }

    public RespondResult(int statusCode, String msg, Map<String, Object> datas) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.datas = datas;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }
}
