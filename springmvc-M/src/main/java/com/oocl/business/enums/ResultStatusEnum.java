package com.oocl.business.enums;

public enum ResultStatusEnum {

    OK(200, "成功"),

    UNAUTHORIZED(401, "没有权限"),

    FORBIDDEN(403, "操作失败"),

    NOT_FOUND(404, "找不到资源"),

    SERVER_ERROR(500, "服务器错误");

    private int code;

    private String msg;

    private ResultStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
