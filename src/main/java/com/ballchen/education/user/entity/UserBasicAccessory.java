package com.ballchen.education.user.entity;

import java.util.Date;

/**
 * Created by ballchen on 2016/7/20.
 * 用户--附件中间表实体类
 */
public class UserBasicAccessory {
    private String id;

    private Date createTime;

    private String userBasicId;

    private String accessoryId;

    public UserBasicAccessory(String id, Date createTime, String userBasicId, String accessoryId) {
        this.id = id;
        this.createTime = createTime;
        this.userBasicId = userBasicId;
        this.accessoryId = accessoryId;
    }

    public UserBasicAccessory() {
    }

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

    public String getUserBasicId() {
        return userBasicId;
    }

    public void setUserBasicId(String userBasicId) {
        this.userBasicId = userBasicId == null ? null : userBasicId.trim();
    }

    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId == null ? null : accessoryId.trim();
    }
}
