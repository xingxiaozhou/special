package cn.vp.service.impl;

import cn.vp.bean.Activity;
import cn.vp.bean.Coupon;
import cn.vp.bean.UserCoupon;
import cn.vp.dao.CouponMapper;
import cn.vp.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券实现类
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponMapper couponMapper;

    /**
     * 获取优惠券
     *
     * @return 优惠券
     */
    @Override
    public Coupon getCoupon() {
        return couponMapper.getCoupon();
    }

    /**
     * 将优惠券添加到用户优惠券表
     *
     * @return 是否成功
     */
    @Override
    public int saveUserCoupon(UserCoupon userCoupon) {
        return couponMapper.saveUserCoupon(userCoupon);
    }

    /**
     * 获取活跃奖励规则
     * @return 列表
     */
    @Override
    public List<Activity> getActive() {
        return couponMapper.getActive();
    }


}
