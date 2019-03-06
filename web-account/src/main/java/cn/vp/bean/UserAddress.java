package cn.vp.bean;

import java.io.Serializable;

/**
 * 用户收货地址
 */
public class UserAddress implements Serializable {
    private Integer id;

    private Integer userId;

    private String addrProvince;

    private String addrCity;

    private String addrArea;

    private String addrAddress;

    private String recipientsPhone;

    private String recipientsName;

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", userId=" + userId +
                ", addrProvince='" + addrProvince + '\'' +
                ", addrCity='" + addrCity + '\'' +
                ", addrArea='" + addrArea + '\'' +
                ", addrAddress='" + addrAddress + '\'' +
                ", recipientsPhone='" + recipientsPhone + '\'' +
                ", recipientsName='" + recipientsName + '\'' +
                '}';
    }

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone;
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }

    private static final long serialVersionUID = 1L;

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

    public String getAddrProvince() {
        return addrProvince;
    }

    public void setAddrProvince(String addrProvince) {
        this.addrProvince = addrProvince == null ? null : addrProvince.trim();
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity == null ? null : addrCity.trim();
    }

    public String getAddrArea() {
        return addrArea;
    }

    public void setAddrArea(String addrArea) {
        this.addrArea = addrArea == null ? null : addrArea.trim();
    }

    public String getAddrAddress() {
        return addrAddress;
    }

    public void setAddrAddress(String addrAddress) {
        this.addrAddress = addrAddress == null ? null : addrAddress.trim();
    }
}