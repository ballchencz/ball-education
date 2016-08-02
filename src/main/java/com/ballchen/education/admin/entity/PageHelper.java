package com.ballchen.education.admin.entity;

import com.ballchen.education.admin.consts.AdminConsts;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 分页
 * Created by ChenZhao on 2016/6/15.
 */
public class PageHelper {
    private String order = "desc";

    private Integer offset;

    private Integer limit;

    @DateTimeFormat(pattern = AdminConsts.DATE_FORMAT_STRING)
    private Date endTime;

    private Integer page;

    private Integer rows;

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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if(page!=null && rows!=null){
            this.offset = (page-1)*rows;
        }
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        if(rows!=null){
            this.limit = rows;
        }
        this.rows = rows;
    }
}
