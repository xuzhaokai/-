package com.oocl.business.model.view;

/**
*@Description 广告 view
*@param
*@return  
*/
public class ComplaintView {

    private String content;
    private Integer complaintType;
    private String createAt;
    private Integer status;

    public ComplaintView() {
    }

    public ComplaintView(String content, Integer complaintType, String createAt, Integer status) {
        this.content = content;
        this.complaintType = complaintType;
        this.createAt = createAt;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(Integer complaintType) {
        this.complaintType = complaintType;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
