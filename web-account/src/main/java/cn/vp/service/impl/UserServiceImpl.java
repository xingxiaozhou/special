package cn.vp.service.impl;

import cn.vp.bean.*;
import cn.vp.dao.UserMapper;
import cn.vp.service.CouponService;
import cn.vp.service.UserService;
import cn.vp.util.Log;
import cn.vp.util.MailUtil;
import cn.vp.util.ResultBean;
import cn.vp.util.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 用户注册登陆 找回密码
 */
@Service
public class UserServiceImpl implements UserService {

    //手机号的正则
    public final static String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0,1,6,7,]))|(18[0-2,5-9]))\\d{8}$";

    @Autowired
    UserMapper userMapper;

    @Autowired
    CouponService couponService;

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 返回结果json
     */
    @Override
    public String addUser(User user) {
        Log.i(this.getClass(), "注册：" + user);
        //创建返回
        ResultBean resultBean = new ResultBean();
        if (userMapper.insertSelective(user) == 1) {
            resultBean.setResultCode("1000");
            resultBean.setMessage("注册成功");
        } else {
            resultBean.setResultCode("200");
            resultBean.setMessage("注册失败");
        }

        return JSON.toJSONString(resultBean);
    }

    /**
     * 登陆
     *
     * @param userName 用户名或手机号
     * @param passWord 密码
     * @return json
     */
    @Override
    public String login(String userName, String passWord) {
        User login = null;
        //查询是否存在
        //判断是否为手机号 如果是手机号则调用手机号的登陆
        boolean isPhone = Pattern.compile(PHONE_PATTERN).matcher(userName).matches();
        if (isPhone)
            login = userMapper.loginByPhone(userName, passWord);
        else
            login = userMapper.login(userName, passWord);
        //创建返回
        ResultBean resultBean = new ResultBean();
        if (login != null && login.getUserName() != null && !("").equals(login.getUserName())) {
            if (!login.getUserStatus()){
                resultBean.setResultCode("300");
                resultBean.setMessage("账号被冻结");
                //结果转换成json存入
                return JSON.toJSONString(resultBean);
            }
            resultBean.setResultCode("1000");
            resultBean.setMessage("登陆成功");
            //结果转换成json存入
            resultBean.setDataInfo(login);
            //获取登陆信息
            getLogin(login);
        } else {
            resultBean.setResultCode("200");
            resultBean.setMessage("账号或密码错误");
        }
        return JSON.toJSONString(resultBean);
    }

    /**
     * 获取登陆信息
     */
    public void getLogin(User user) {
        Log.i(this.getClass(), "登陆成功获取登陆信息：userid为：" + user.getId());
        UserLogin userLogin = userMapper.getLogin(user.getId());
        if (userLogin == null) {
            //如果登陆信息为空 则表示第一次登陆
            userLogin = new UserLogin();
            userLogin.setUserId(user.getId());
            userLogin.setLastLoginDate(new Date());
            userLogin.setLoginDays(1);
            int i = userMapper.addLogin(userLogin);
            if (i > 0)
                Log.i(this.getClass(), "添加登陆信息成功" + userLogin);
            else
                Log.i(this.getClass(), "添加登陆信息失败" + userLogin);
            //1、新用户首次登录即可获赠新手奖品，用注册邮箱发送，例如餐饮优惠卷（优惠码）。
            //获取随机优惠券
            Coupon coupon = couponService.getCoupon();
            sendCoupon(user,coupon);
        } else {
            //有登陆信息 则修改登陆信息
            //获取最后一天登陆时间
            Date lastDate = userLogin.getLastLoginDate();
            boolean isSameDate = TimeUtil.isSameDate(lastDate, new Date());
            //如果是想同不需要修改 不同修改后上传
            if (!isSameDate) {
                //登陆天数+1天
                userLogin.setLoginDays(userLogin.getLoginDays() + 1);
                //最后登陆日期设置为今天
                userLogin.setLastLoginDate(new Date());
                //修改到数据库
                int i = userMapper.updateLogin(userLogin);
                if (i > 0) {
                    Log.i(this.getClass(), "添加登陆信息成功" + userLogin);
                } else
                    Log.i(this.getClass(), "添加登陆信息失败" + userLogin);
                // TODO: 2018/5/24 0024  1、活跃用户（比如累计登陆100天），即可获赠活跃奖品，用注册邮箱发送，例如餐饮优惠卷（优惠码）。活跃规则可以后台配置。
                List<Activity> active = couponService.getActive();
                for (Activity a : active) {
                    if (a.getActivityDays().equals(userLogin.getLoginDays())) {
                        sendCoupon(user,a.getCoupon());
                    }
                }

            }

        }
    }

    /**
     * 获取优惠券
     */
    public void sendCoupon(User user,Coupon coupon) {
        //不为空则存入用户的优惠券表
        if (coupon != null) {
            Log.i(this.getClass(), "获取优惠券" + coupon);
            //创建用户优惠券对象
            UserCoupon userCoupon = new UserCoupon();
            //赋值
            userCoupon.setCoupontId(coupon.getId());
            userCoupon.setUserId(user.getId());
            //保存到数据库
            couponService.saveUserCoupon(userCoupon);
            //发送邮件
            String text = "赠送你" + coupon.getCouponName() + coupon.getCouponCode() + "元优惠券，已自动发送你的钱包请查收";
            MailUtil.send(text, user.getUserEmail());
        } else {
            Log.i(this.getClass(), "随机获取优惠券失败");
        }
    }


    @Override
    public List<User> selectUsers() {
        return userMapper.selectAll();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int selecrUsername(String username) {
        return userMapper.selectUsername(username);
    }

    @Override
    public List<User> getUsers(String userName, String startCreateTime, String endCreateTime) {
        return userMapper.getUsers(userName, startCreateTime, endCreateTime);
    }

    @Override
    public int updateAdvert(Integer id, boolean status) {
        return userMapper.updateAdvert(id, status);
    }

    @Override
    public int selectEmal(String email) {
        return userMapper.selectEmal(email);
    }


    /**
     * 找回密码
     *
     * @param userEmail    email
     * @param userpassword 新密码
     * @return
     */
    @Override
    public String updateuserpwd(String userEmail, String userpassword) {
        ResultBean resultBean = new ResultBean();
        //修改密码
        int i = userMapper.updateuserpwd(userEmail, userpassword);
        if (i <= 0) {
            resultBean.setResultCode("200");
            resultBean.setMessage("修改失败");
        } else {
            resultBean.setResultCode("1000");
            resultBean.setMessage("修改成功");
        }
        return JSON.toJSONString(resultBean);
    }
}
