package com.oocl.business.enums;

public enum JmsTypeEnum {

    AUDIT("1", "审核"),

    COMPLAINT("2", "投诉");

    private String type;


    private String msg;

    private JmsTypeEnum(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
