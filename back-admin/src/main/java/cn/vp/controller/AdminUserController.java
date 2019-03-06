package cn.vp.controller;

import cn.vp.bean.Admin;
import cn.vp.service.AdminService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 管理员管理
 */
@Controller
public class AdminUserController {


    @Autowired
    AdminService adminService;

    /**
     * 跳转到后台用户管理界面
     *
     * @return 用户管理
     */
    @RequestMapping("/adminUser.htm")
    public String adminUser() {
        return "adminUser";
    }


    /**
     * 获取后台管理员列表
     *
     * @param pageNum 当前页数
     * @return json
     */
    @RequestMapping("/adminUsers.do")
    @ResponseBody
    public String adminUsers(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Log.i(this.getClass(), "获取管理员列表  pageNum：" + pageNum);
        //分页
        PageHelper.startPage(pageNum, 5);
        //获取后台管理员
        List<Admin> admins = adminService.getAdmins();
        //将数据库获取到数据放入pageInfo里  在页面上获取 需要通过pageInfo.list获取
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        //因为里面有date类型数据所以需要转换一下
        System.err.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    /**
     * 跳转到新增页面
     *
     * @return 新增页面
     */
    @RequestMapping("adminUserHandle.htm")
    public String adminUserHandle() {
        return "adminUserHandle";
    }

    @RequestMapping("addAdminUser.do")
    @ResponseBody
    public String addAdminUser(Admin admin) {
        Log.i(this.getClass(), "新增：" + admin);
        int i = adminService.selectByName(admin.getAdminUser());
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        if (i == 0) {
            admin.setCreateTime(new Date());
            int i1 = adminService.addAdmin(admin);
            if (i1 > 0)
                jsonObject.put("isAdd", "添加管理员用户成功");
            else
                jsonObject.put("isAdd", "添加管理员用户失败");
        } else
            jsonObject.put("isAdd", "管理员已存在");
        return jsonObject.toJSONString();
    }


    /**
     * 删除管理员
     * @param id 管理员id
     * @return json
     */
    @RequestMapping("delAdminUser.do")
    @ResponseBody
    public String delAdminUser(Integer[] id) {
        Log.i(this.getClass(), "删除管理员  id：" + Arrays.toString(id));
        int i = adminService.delectAdmin(id);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        if (i > 0) {
            jsonObject.put("isDel", "删除成功");
            jsonObject.put("num", id);
        } else
            jsonObject.put("isDel", "删除失败");
        return jsonObject.toJSONString();
    }


}
