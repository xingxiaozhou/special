package cn.vp.service.impl;

import cn.vp.bean.Coupon;
import cn.vp.bean.CouponType;
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
     * 获取优惠券集合
     * @return 优惠券集合
     */
    @Override
    public List<Coupon> getCoupons() {
        return couponMapper.getCoupons();
    }

    /**
     * 根据id获取优惠券
     * @param id id
     * @return 优惠券
     */
    @Override
    public Coupon getCouponByid(Integer id) {
        return couponMapper.getCouponById(id);
    }

    /**
     * 获取优惠券类别集合
     * @return 类别集合
     */
    @Override
    public List<CouponType> getCouponType() {
        return couponMapper.getCouponType();
    }

    @Override
    public int couponAdd(Coupon coupon) {
        return couponMapper.addCoupon(coupon);
    }

    @Override
    public int couponUpdate(Coupon coupon) {
        return couponMapper.couponUpdate(coupon);
    }


}
