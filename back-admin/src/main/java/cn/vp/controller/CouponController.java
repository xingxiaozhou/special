package cn.vp.controller;

import cn.vp.bean.Coupon;
import cn.vp.bean.CouponType;
import cn.vp.bean.Menu;
import cn.vp.service.CouponService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 优惠券控制层
 */
@Controller
public class CouponController {

    @Autowired
    CouponService couponService;

    /**
     * 获取优惠券列表
     *
     * @param pageNum 页码
     * @return
     */
    @RequestMapping("getCoupon.do")
    @ResponseBody
    public String getCoupon(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, HttpServletRequest request) {
        Log.i(this.getClass(), "获取优惠券列表");
        HttpSession session = request.getSession();
        //获取优惠券分类
        List<CouponType> couponType = couponService.getCouponType();
        session.setAttribute("couponType", couponType);
        //分页 now 页码  pagesize 显示几条
        PageHelper.startPage(pageNum, 5);
        List<Coupon> coupons = couponService.getCoupons();
        if (coupons==null){
            Log.i(this.getClass(), "获取的优惠券列表为null" );
            return "";
        }
        Log.i(this.getClass(), "获取的优惠券列表" + coupons);
        //将数据库获取到数据放入pageInfo里  在jsp页面上获取 需要通过pageInfo.list获取
        PageInfo<Coupon> pageInfo = new PageInfo<Coupon>(coupons);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        return jsonObject.toJSONString();
    }


    /**
     * 根据id获取优惠券
     *
     * @param id 优惠券id
     * @return 跳转页面
     */
    @RequestMapping("getCouponById.do")
    public String getCouponById(@RequestParam(value = "id") Integer id, Model model) {
        Log.i(this.getClass(), "根据id获取优惠券:" + id);
        Coupon coupon = couponService.getCouponByid(id);
        model.addAttribute("coupon", coupon);
        return "couponHandle";
    }

    /**
     * 跳转
     *
     * @return 跳转页面
     */
    @RequestMapping("couponHandle.htm")
    public String couponHandle() {
        return "couponHandle";
    }

    /**
     * 跳转
     *
     * @return 跳转页面
     */
    @RequestMapping("coupon.htm")
    public String coupon() {
        return "coupon";
    }

    /**
     * 新增优惠券
     * @param coupon 优惠券
     * @return 跳转主页
     */
    @RequestMapping("couponAdd.do")
    public String couponAdd(Coupon coupon) {
        Log.i(this.getClass(), "新增优惠券：" + coupon);
        int i = couponService.couponAdd(coupon);
        if (i>0)
            Log.i(this.getClass(),"新增成功");
        else
            Log.i(this.getClass(),"新增失败");
        return "coupon";
    }


    /**
     * 修改优惠券
     * @param coupon 优惠券
     * @return 跳转到主页
     */
    @RequestMapping("couponUpdate.do")
    public String couponUpdate(Coupon coupon) {
        Log.i(this.getClass(), "修改优惠券：" + coupon);
        int i = couponService.couponUpdate(coupon);
        if (i>0)
            Log.i(this.getClass(),"修改成功");
        else
            Log.i(this.getClass(),"修改失败");
        return "coupon";
    }
}
