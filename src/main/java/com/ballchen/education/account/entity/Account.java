package com.ballchen.education.account.entity;

import com.ballchen.education.admin.consts.AdminConsts;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Account {
    private String id;

    @DateTimeFormat(pattern = AdminConsts.DATE_FORMAT_STRING)
    private Date createTime;

    @DateTimeFormat(pattern = AdminConsts.DATE_FORMAT_STRING)
    private Date endTime;

    private String accountName;

    private String password;

    private Boolean denied;

    private String mark;

    private String order;

    private Integer offset;

    private Integer limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getDenied() {
        return denied;
    }

    public void setDenied(Boolean denied) {
        this.denied = denied;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}