package cn.vp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类
 */
public class Admin implements Serializable {
    private Integer id;

    private String adminUser;

    private String adminPassword;

    private String adminEmail;

    private String adminPhone;

    private Date createTime;

    private Boolean isSuperadmin=false;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminUser='" + adminUser + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", createTime=" + createTime +
                ", isSuperadmin=" + isSuperadmin +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser == null ? null : adminUser.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail == null ? null : adminEmail.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsSuperadmin() {
        return isSuperadmin;
    }

    public void setIsSuperadmin(Boolean isSuperadmin) {
        this.isSuperadmin = isSuperadmin;
    }
}