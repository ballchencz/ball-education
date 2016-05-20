package com.ballchen.education.user.entity;

import java.util.Date;

public class UserBasic {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 性别，false.女；true.男
     */
    private Boolean sex;

    /**
     *出生日期
     */
    private Date birthday;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 电话
     */
    private String phone;

    /**
     * 备注
     */
    private String mark;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}