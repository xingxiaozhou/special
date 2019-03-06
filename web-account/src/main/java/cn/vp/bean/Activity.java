package cn.vp.bean;

import java.io.Serializable;

/**
 * 活跃规则
 */
public class Activity implements Serializable{
    private Integer id;

    private Integer activityDays;

    private Coupon coupon;

    private Boolean isopen;

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityDays=" + activityDays +
                ", coupon=" + coupon +
                ", isopen=" + isopen +
                '}';
    }

    public Integer getActivityDays() {
        return activityDays;
    }

    public void setActivityDays(Integer activityDays) {
        this.activityDays = activityDays;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }
}
