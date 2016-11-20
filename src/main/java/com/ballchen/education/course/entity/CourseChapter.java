package com.ballchen.education.course.entity;

import com.ballchen.education.consts.PublicConsts;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CourseChapter {
    private String id;

    private Date createTime;

    private String chapterName;

    private String chapterType;

    @DateTimeFormat(pattern = PublicConsts.DATETIME_FORMAT_STRING)
    private Date planBeginTime;

    @DateTimeFormat(pattern = PublicConsts.DATETIME_FORMAT_STRING)
    private Date planEndTime;

    @DateTimeFormat(pattern = PublicConsts.DATETIME_FORMAT_STRING)
    private Date realBeginTime;

    @DateTimeFormat(pattern = PublicConsts.DATETIME_FORMAT_STRING)
    private Date realEndTime;

    private String courseId;

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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getChapterType() {
        return chapterType;
    }

    public void setChapterType(String chapterType) {
        this.chapterType = chapterType == null ? null : chapterType.trim();
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Date getRealBeginTime() {
        return realBeginTime;
    }

    public void setRealBeginTime(Date realBeginTime) {
        this.realBeginTime = realBeginTime;
    }

    public Date getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(Date realEndTime) {
        this.realEndTime = realEndTime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }
}