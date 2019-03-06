package cn.vp.controller;

import cn.vp.bean.*;
import cn.vp.service.LinkService;
import cn.vp.service.UserAddressService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户收货地址
 */
@Controller
public class UserAddressController {

    @Autowired
    UserAddressService userAddressService;

    @Autowired
    LinkService linkService;

    /**
     * 根据用户id获取他的所有送货地址
     *
     * @param pageNum 第几页
     * @param userId  用户id
     * @return
     */
    @RequestMapping("/getAddressById.do")
    @ResponseBody
    public String getAddress(HttpServletRequest request, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum
            , @RequestParam("userId") Integer userId) {
        Log.i(this.getClass(), "用户id为：" + userId);
        PageHelper.startPage(pageNum, 5);
        List<UserAddress> userAddress = userAddressService.getAddressById(userId);
        //将数据库获取到数据放入pageInfo里  在页面上获取 需要通过pageInfo.list获取
        PageInfo<UserAddress> pageInfo = new PageInfo<UserAddress>(userAddress);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        System.err.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }


    /**
     * 新增收货地址
     *
     * @param userAddress
     * @return
     */
    @RequestMapping("addAddr.do")
    @ResponseBody
    public String getAddAddr(UserAddress userAddress) {
        Log.i(this.getClass(), "新增地址：用户id为" + userAddress.getUserId() + "  地址为" + userAddress);
        int i = userAddressService.addAddress(userAddress);
        if (i > 0) {
            return JSON.toJSONString("1000");
        } else {
            return JSON.toJSONString("0");
        }
    }

    /**
     * 根据id查找收货地址
     *
     * @param id id
     * @return
     */
    @RequestMapping("queryAddrById.do")
    public String queryAddrById(Integer id, Model model) {
        Log.i(this.getClass(), "获取收货地址的id为：" + id);
        UserAddress address = userAddressService.getAddress(id);
        model.addAttribute("addr", address);
        return "addressUpdate";
    }


    /**
     * 根据id删除收货地址
     *
     * @param id
     * @return
     */
    @RequestMapping("delAddr")
    @ResponseBody
    public String delAddr(Integer id) {
        Log.i(this.getClass(), "删除收货地址的id为:" + id);
        int i = userAddressService.delectAddress(id);
        JSONObject jsonObject = new JSONObject();
        if (i > 0) {
            //删除成功
            jsonObject.put("isDel", 1000);
            jsonObject.put("num", id);
        } else {
            //删除失败
            jsonObject.put("isDel", 0);
        }
        return jsonObject.toJSONString();
    }

    /**
     *
     * 修改用户收货地址
     * @param userAddress addr
     * @return
     */
    @RequestMapping("updateAddr")
    @ResponseBody
    public String updateAddr(UserAddress userAddress){
        Log.i(this.getClass(), "修改：用户id为" + userAddress.getUserId() + "  地址为" + userAddress);
        int i = userAddressService.updateAddress(userAddress);
        if (i > 0) {
            return JSON.toJSONString("1000");
        } else {
            return JSON.toJSONString("0");
        }
    }



    /**
     * 获取省
     *
     * @return
     */
    @RequestMapping("provinces")
    @ResponseBody
    public String getProvinces() {
        List<Province> province = linkService.getProvince();
        for (Province p : province
                ) {
            System.err.println(p);
        }
        return JSON.toJSONString(province);
    }

    /**
     * 获取市
     *
     * @return
     */
    @RequestMapping("cities")
    @ResponseBody
    public String getCities(String provinceId) {
        List<City> citys = linkService.getCity(provinceId);
        return JSON.toJSONString(citys);
    }


    /**
     * 获取县/区
     *
     * @return
     */
    @RequestMapping("areas")
    @ResponseBody
    public String getAreas(String cityId) {
        List<Area> areas = linkService.getArea(cityId);
        return JSON.toJSONString(areas);
    }
}
