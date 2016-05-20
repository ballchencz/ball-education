package com.ballchen.education.user.entity;

import java.util.Date;

public class UserBasic {
    /**
     * ����ID
     */
    private String id;

    /**
     * ����ʱ��
     */
    private Date createTime;

    /**
     * �û�����
     */
    private String userName;

    /**
     * �Ա�false.Ů��true.��
     */
    private Boolean sex;

    /**
     *��������
     */
    private Date birthday;

    /**
     * �����ʼ�
     */
    private String email;

    /**
     * ���֤����
     */
    private String idNumber;

    /**
     * �绰
     */
    private String phone;

    /**
     * ��ע
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