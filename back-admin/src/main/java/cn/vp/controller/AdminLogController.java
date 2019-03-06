package cn.vp.controller;

import cn.vp.bean.AdminLoginLog;
import cn.vp.service.AdminLoginLogService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 管理员登陆记录
 */
@Controller
public class AdminLogController {

    @Autowired
    AdminLoginLogService adminLoginLogService;


    /**
     * 跳转到登陆记录页面
     *
     * @return 页面
     */
    @RequestMapping("adminLog.htm")
    public String adminLog() {
        return "adminLog";
    }


    /**
     * 查询管理员登陆记录
     * @param pageNum 第几页
     * @param adminUser 用户名
     * @param adStarttime 开始时间
     * @param adEndtime 结束时间
     * @return json
     */
    @RequestMapping("queryAdminLogs.do")
    @ResponseBody
    public String queryAdminLogs(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "adminUser") String adminUser,
                                 @RequestParam(value = "adStarttime", required = false) String adStarttime,
                                 @RequestParam(value = "adEndtime", required = false) String adEndtime) {
        Log.i(this.getClass(), "获取管理员列表  pageNum：" + pageNum);
        //分页
        PageHelper.startPage(pageNum, 5);
        List<AdminLoginLog> adminLoginLogs = adminLoginLogService.selectByNameAndTime(adminUser, adStarttime, adEndtime);
       //将数据库获取到数据放入pageInfo里  在页面上获取 需要通过pageInfo.list获取
        PageInfo<AdminLoginLog> pageInfo = new PageInfo<>(adminLoginLogs);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        System.err.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();

    }


}
