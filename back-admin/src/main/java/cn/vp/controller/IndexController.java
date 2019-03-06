package cn.vp.controller;

import cn.vp.bean.Admin;
import cn.vp.bean.AdminLoginLog;
import cn.vp.service.AdminLoginLogService;
import cn.vp.service.AdminService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * 管理员登陆
 */
@Controller
public class IndexController {
    @Autowired
    AdminService adminService;

    //管理员登陆记录
    @Autowired
    AdminLoginLogService adminLoginLogService;

    /**
     * 首页登陆
     * @param username 用户名
     * @param password 密码
     * @return 跳转
     */
    @RequestMapping(value = "/index.do")
    @ResponseBody
    public String index(HttpServletRequest request, @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password) {
        Log.i(this.getClass(),"管理员登陆：username="+username);
        System.err.println("username:" + username+"密码："+password);
        Admin login = adminService.login(username,password);
        JSONObject jsonObject = new JSONObject();
        if (null==login||login.getAdminUser().equals("")){
            jsonObject.put("message","用户名密码不正确！");
            return jsonObject.toJSONString();
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginUser",login);
        //管理员登陆记录
        AdminLoginLog adminLogin=new AdminLoginLog();
        adminLogin.setLoginTime(new Date());
        adminLogin.setAdminId(login.getId());
        adminLogin.setAdminUser(login.getAdminUser());
        //添加到数据库
        Log.i(this.getClass(),"登陆记录：adminLogin="+adminLogin);
        int i = adminLoginLogService.addAdminLoginLog(adminLogin);
        //判断是否插入成功
        if (i>0){
            Log.i(this.getClass(),"登陆记录插入成功：adminLogin="+adminLogin);
        }else {
            Log.e(this.getClass(),"插入失败:adminLogin"+adminLogin);
        }
        jsonObject.put("message","登陆成功");
        return jsonObject.toJSONString();
//        return "登陆成功";
    }

}
