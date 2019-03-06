package cn.vp.controller;

import cn.vp.bean.User;
import cn.vp.service.UserService;
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
 * 客户基本信息
 */
@Controller
public class UserMangerController {

    @Autowired
    UserService userService;

    //跳转到页面
    @RequestMapping("customerBasicInformation.htm")
    public String customerBasicInformation() {
        return "customerBasicInformation";
    }


    /**
     * 获取用户列表
     * @param pageNum 页码
     * @param userName 用户名
     * @param startCreateTime 开始时间
     * @param endCreateTime 结束时间
     * @return json结果集
     */
    @RequestMapping("getUsers.do")
    @ResponseBody
    public String getUsers(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "userName") String userName,
                           @RequestParam(value = "startCreateTime", required = false) String startCreateTime,
                           @RequestParam(value = "endCreateTime", required = false) String endCreateTime) {
        Log.i(this.getClass(), "多条件查询  用户名：" + userName + "  开始时间：" + startCreateTime + "  结束时间：" + endCreateTime);
        PageHelper.startPage(pageNum, 5);
        List<User> userList = userService.getUsers(userName, startCreateTime, endCreateTime);
        //将数据库获取到数据放入pageInfo里  在页面上获取 需要通过pageInfo.list获取
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);

        //因为里面有date类型数据所以需要转换一下
        System.err.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }


    /**
     * 更改用户状态
     * @param id id
     * @param status 状态
     * @return 结果集
     */
    @RequestMapping("changeStatus.do")
    @ResponseBody
    public String changeStatus(@RequestParam("id") Integer id, @RequestParam(value = "status", required = false, defaultValue = "false") boolean status) {
        Log.i(this.getClass(), "更改用户状态id为：" + id + "状态为:" + status);
        int i = userService.updateAdvert(id, !status);
        //操作是否成功
        boolean result;
        if (i > 0) {
            result = true;
        } else
            result = false;
        JSONObject jsonObject = new JSONObject();
        //启动还是禁用
        jsonObject.put("status", !status);
        jsonObject.put("result", result);
        return jsonObject.toJSONString();
    }

}
