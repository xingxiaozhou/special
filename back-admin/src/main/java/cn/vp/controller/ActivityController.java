package cn.vp.controller;

import cn.vp.bean.Activity;
import cn.vp.bean.Coupon;
import cn.vp.bean.CouponType;
import cn.vp.service.ActivityService;
import cn.vp.service.CouponService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSONArray;
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
 * 活跃奖励
 */
@Controller
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    CouponService couponService;

    /**
     * 跳转
     *
     * @return 活跃奖励
     */
    @RequestMapping("activity.htm")
    public String coupon() {
        return "activity";
    }


    /**
     * 跳转添加
     *
     * @return 活跃奖励添加页面
     */
    @RequestMapping("activityHandle.htm")
    public String activityHandle() {
        return "activityHandle";
    }

    /**
     * 获取活跃奖励列表
     *
     * @param pageNum 页码
     * @return 结果集
     */
    @RequestMapping("getActivity.do")
    @ResponseBody
    public String getActivity(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              HttpServletRequest request) {
        Log.i(this.getClass(), "活跃奖励列表");
        HttpSession session = request.getSession();
//        //获取优惠券分类
        List<Coupon> coupons = couponService.getCoupons();
        session.setAttribute("coupons", coupons);
        //分页 now 页码  pagesize 显示几条
        PageHelper.startPage(pageNum, 5);
        List<Activity> active = activityService.getActive();
        if (active == null) {
            Log.i(this.getClass(), "获取活跃奖励列表为null");
            return "";
        }
        Log.i(this.getClass(), "获取活跃奖励列表的数量" + active);
        //将数据库获取到数据放入pageInfo里  在jsp页面上获取 需要通过pageInfo.list获取
        PageInfo<Activity> pageInfo = new PageInfo<Activity>(active);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        return jsonObject.toJSONString();
    }


    /**
     * 根据id获取活跃奖励
     *
     * @param id    id
     * @param model 作用域
     * @return 跳转页面
     */
    @RequestMapping("getActivityById.do")
    public String getActivityById(@RequestParam(value = "id") Integer id, Model model) {
        Log.i(this.getClass(), "根据id获取活跃奖励列表:" + id);
        Activity activity = activityService.getActivityById(id);
        Log.i(this.getClass(), "获得的活跃奖励为" + activity);
        model.addAttribute("activity", activity);
        return "activityHandle";
    }


    /**
     * 新增活跃奖励规则
     *
     * @param activity 活跃奖励规则
     * @return 跳转页面
     */
    @RequestMapping("activityAdd.do")
    public String activityAdd(Activity activity) {
        Log.i(this.getClass(), "活跃奖励规则:" + activity);
        int i = activityService.activityAdd(activity);
        if (i > 0)
            Log.i(this.getClass(), "新增成功");
        else
            Log.i(this.getClass(), "新增失败");
        return "activity";
    }

    /**
     * 修改活跃奖励规则
     *
     * @param activity 活跃奖励规则
     * @return 跳转到主页
     */
    @RequestMapping("activityUpdate.do")
    public String activityUpdate(Activity activity) {
        Log.i(this.getClass(), "修改优惠券：" + activity);
        int i = activityService.activityUpdate(activity);
        if (i > 0)
            Log.i(this.getClass(), "修改成功");
        else
            Log.i(this.getClass(), "修改失败");
        return "activity";
    }


    /**
     * 设置开启关闭
     *
     * @param id     活跃奖励id
     * @param isOpen 是否启用
     * @return 结果集
     */
    @RequestMapping("isOpen.do")
    @ResponseBody
    public String activityIsOpen(@RequestParam("id") Integer id, @RequestParam("isOpen") boolean isOpen) {
        Log.i(this.getClass(), "更改活跃奖励是否开启id为：" + id + "状态为:" + isOpen);
        int i = activityService.activityIsOpen(id, !isOpen);
        //操作是否成功
        boolean result;
        if (i > 0) {
            result = true;
        } else
            result = false;
        JSONObject jsonObject = new JSONObject();
        //启动还是禁用
        jsonObject.put("isOpen", !isOpen);
        jsonObject.put("result", result);
        return jsonObject.toJSONString();
    }


    public static void main(String[] args) {
        String json = "[[\"\\u4f53\\u9a8c\\u7248\", 1], [\"\\u6b63\\u5f0f\\u7248\", 1]]";
        JSONArray jsonArray=JSONArray.parseArray(json);
        JSONArray JSONArray1 = jsonArray.getJSONArray(0);
        System.err.println(JSONArray1);
    }


}
