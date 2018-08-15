package com.oocl.business.enums;

public enum StatusEnum {

	BUSINESS_STATUS_SUCCESS(1,"已经提交注册"),
	BUSINESS_STATUS_NONE(0,"没提交注册过"),
	BUSINESS_STATUS_INSTORE(2,"已结提交注册过 并 入库"),


    MERCHANT_STATUS_NO(-1,"未提交"),
	MERCHANT_STATUS_PENDING(0,"待处理"),
	MERCHANT_STATUS_AGREE(1,"同意"),
	MERCHANT_STATUS_DISAGREE(2,"不同意"),

    ORDERS_STATUS_HAVE(0,"已经接单"),
    ORDERS_STATUS_DELIVERING(1,"配送中"),
    ORDERS_STATUS_ARRIVE(2,"已到达"),
    ORDERS_STATUS_REFUSE(3,"被拒"),
    ORDERS_STATUS_WAITING(4,"等待接单"),
    ORDERS_STATUS_CANCEL(5,"取消状态"),
    ORDERS_STATUS_FINISH(6,"完成状态");

    private int value;

    private String msg;

    private StatusEnum(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
