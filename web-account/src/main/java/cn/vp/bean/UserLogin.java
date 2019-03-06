package cn.vp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登陆记录
 */
public class UserLogin implements Serializable {
    public Integer id;

    public Integer userId;

    public Integer loginDays;

    public Date  lastLoginDate;

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", userId=" + userId +
                ", loginDays=" + loginDays +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLoginDays() {
        return loginDays;
    }

    public void setLoginDays(Integer loginDays) {
        this.loginDays = loginDays;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
