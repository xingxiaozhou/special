package cn.vp.bean;

import java.io.Serializable;

/**
 * 优惠券
 */
public class Coupon implements Serializable {
    private Integer id;

    private Integer couponCode;//优惠券金额

    private Integer couponType;//优惠券类别id

    private String couponName;//优惠券类别名称

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", couponCode=" + couponCode +
                ", couponType=" + couponType +
                ", couponName='" + couponName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Integer couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
