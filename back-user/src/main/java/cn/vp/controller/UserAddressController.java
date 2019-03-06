package cn.vp.controller;

import cn.vp.bean.User;
import cn.vp.bean.UserAddress;
import cn.vp.service.UserAddressService;
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
 * 用户地址
 */
@Controller
public class UserAddressController {

    @Autowired
    UserAddressService userAddressService;

    //获取用户地址
    @RequestMapping("/usersAddress.do")
    @ResponseBody
    public String getUserAddress(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "username",required = false,defaultValue = "")String username) {
        Log.i(this.getClass(), "查询用户地址:"+username);
        PageHelper.startPage(pageNum, 5);
        List<UserAddress> userAddresses = userAddressService.selectAll(username);

        //将数据库获取到数据放入pageInfo里  在页面上获取 需要通过pageInfo.list获取
        PageInfo<UserAddress> pageInfo = new PageInfo<UserAddress>(userAddresses);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        for (UserAddress u:pageInfo.getList()
                ) {
            System.err.println(u);
        }

        return jsonObject.toJSONString();

    }


}
