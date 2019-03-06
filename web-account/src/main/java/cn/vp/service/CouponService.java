package cn.vp.service;

import cn.vp.bean.Activity;
import cn.vp.bean.Coupon;
import cn.vp.bean.UserCoupon;

import java.util.List;

/**
 * 优惠券
 */
public interface CouponService {

    Coupon getCoupon();

    int saveUserCoupon(UserCoupon userCoupon);

    List<Activity> getActive();
}
