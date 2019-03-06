package cn.vp.bean;

import java.io.Serializable;

/**
 * 用户获取的优惠券
 */
public class UserCoupon implements Serializable {

    private Integer id;//id
    private Integer userId;//用户id
    private Integer coupontId;//优惠券id
    private Boolean isuse;

    @Override
    public String toString() {
        return "UserCoupon{" +
                "id=" + id +
                ", userId=" + userId +
                ", coupontId=" + coupontId +
                ", isuse=" + isuse +
                '}';
    }

    public Boolean getIsuse() {
        return isuse;
    }

    public void setIsuse(Boolean isuse) {
        this.isuse = isuse;
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

    public Integer getCoupontId() {
        return coupontId;
    }

    public void setCoupontId(Integer coupontId) {
        this.coupontId = coupontId;
    }
}
