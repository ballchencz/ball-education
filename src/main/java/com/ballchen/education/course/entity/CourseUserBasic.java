package com.ballchen.education.course.entity;

import java.util.Date;

public class CourseUserBasic {
    private String id;

    private Date createTime;

    private String courseId;

    private String userBasicId;

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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getUserBasicId() {
        return userBasicId;
    }

    public void setUserBasicId(String userBasicId) {
        this.userBasicId = userBasicId == null ? null : userBasicId.trim();
    }
}