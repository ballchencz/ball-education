package com.ballchen.education.user.entity;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.admin.consts.AdminConsts;
import com.ballchen.education.security.entity.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class UserBasic {
    private String id;//ID

    @DateTimeFormat(pattern = AdminConsts.DATE_FORMAT_STRING)
    private Date createTime;//创建时间

    private String userName;//用户姓名

    private String nickName;//昵称

    private Boolean sex;//性别

    @DateTimeFormat(pattern = AdminConsts.DATE_FORMAT_STRING)
    private Date birthday;//生日

    @DateTimeFormat(pattern = AdminConsts.DATE_FORMAT_STRING)
    private Date endBirthday;//生日查询结束

    private String email;//邮箱

    private String idNumber;//身份证号码

    private String phone;//手机号码

    private String homePhone;//家庭电话

    private String accountId;//账户ID

    private String mark;//备注

    private String description;//描述

    private Boolean realNameValid;//实名认证

    private Boolean denied;//禁用

    private String deniedReason;//禁用原因

    private List<Role> roles;//角色组

    private Account account;//账户

    private List<Accessory> accessories;//用户附件集合

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
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

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone == null ? null : homePhone.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getRealNameValid() {
        return realNameValid;
    }

    public void setRealNameValid(Boolean realNameValid) {
        this.realNameValid = realNameValid;
    }

    public String getDeniedReason() {
        return deniedReason;
    }

    public void setDeniedReason(String deniedReason) {
        this.deniedReason = deniedReason;
    }

    public Boolean getDenied() {
        return denied;
    }

    public void setDenied(Boolean denied) {
        this.denied = denied;
    }

    public Date getEndBirthday() {
        return endBirthday;
    }

    public void setEndBirthday(Date endBirthday) {
        this.endBirthday = endBirthday;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }
}