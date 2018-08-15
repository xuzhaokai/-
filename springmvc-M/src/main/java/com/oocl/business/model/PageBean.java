package com.oocl.business.model;

import java.util.List;

public class PageBean<T> {

    private Integer total;// 总记录数

    private Integer totalPage;// 总页数

    private List<T> rows;

    public PageBean(){

    }

    public PageBean(Integer total, Integer totalPage, List<T> rows) {
        this.total = total;
        this.totalPage = totalPage;
        this.rows = rows;
    }

    public PageBean(List<T> rows, Integer total, Integer limit){
        this.rows = rows;
        this.total = total;
        this.totalPage = (int) Math.ceil(1.0 * total / limit);
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
