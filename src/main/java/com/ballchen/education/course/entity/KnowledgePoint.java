package com.ballchen.education.course.entity;

import com.ballchen.education.consts.PublicConsts;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class KnowledgePoint {
    private String id;

    @DateTimeFormat(pattern = PublicConsts.DATE_FORMAT_STRING)
    private Date createTime;

    private String knowledgeName;

    private String courseId;

    private String description;

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

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName == null ? null : knowledgeName.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}