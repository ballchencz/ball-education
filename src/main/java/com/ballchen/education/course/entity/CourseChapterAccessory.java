package com.ballchen.education.course.entity;

import java.util.Date;

public class CourseChapterAccessory {
    private String id;

    private Date createTime;

    private String courseChapterId;

    private String accessoryId;

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

    public String getCourseChapterId() {
        return courseChapterId;
    }

    public void setCourseChapterId(String courseChapterId) {
        this.courseChapterId = courseChapterId == null ? null : courseChapterId.trim();
    }

    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId == null ? null : accessoryId.trim();
    }
}