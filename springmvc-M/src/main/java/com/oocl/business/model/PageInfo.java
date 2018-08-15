package com.oocl.business.model;

public class PageInfo {

    private int page; //页码

    private int size; //每页记录数

    private String order; //排序字段

    private String descOrAsc;

    public PageInfo(){

    }

    public PageInfo(int page, int size, String order, String descOrAsc) {
        this.page = page;
        this.size = size;
        this.order = order;
        this.descOrAsc = descOrAsc;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDescOrAsc() {
        return descOrAsc;
    }

    public void setDescOrAsc(String descOrAsc) {
        this.descOrAsc = descOrAsc;
    }
}
