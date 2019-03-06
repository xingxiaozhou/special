package cn.vp.service;

import cn.vp.bean.Coupon;
import cn.vp.bean.CouponType;

import java.util.List;

/**
 * 优惠券
 */
public interface CouponService {

    List<Coupon> getCoupons();

    Coupon getCouponByid(Integer id);

    List<CouponType> getCouponType();

    int couponAdd(Coupon coupon);

    int couponUpdate(Coupon coupon);
}
