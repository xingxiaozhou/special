package cn.vp.bean;

import java.io.Serializable;

/**
 * 优惠券类别
 */
public class CouponType implements Serializable {
    private  Integer id;

    private String couponName;


    @Override
    public String toString() {
        return "CouponType{" +
                "id=" + id +
                ", couponName='" + couponName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
