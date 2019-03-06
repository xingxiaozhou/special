package cn.vp.controller;

import cn.vp.bean.Advert;
import cn.vp.service.AdvertService;
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
import java.util.List;

/**
 * 广告管理
 */
@Controller
public class AdvertController {

    @Autowired
    AdvertService advertService;

    @RequestMapping("advert.htm")
    public String advert() {
        return "advert";
    }


    /**
     * 跳转到新增页面
     *
     * @return 新增
     */
    @RequestMapping("advertHandle.htm")
    public String addAdvert() {
        return "advertHandle";
    }

    /**
     * 新增广告
     *
     * @param advert 广告
     * @return 跳转到首页
     */
    @RequestMapping("addAdvert.do")
    public String addAdvert(Advert advert) {
        Log.i(this.getClass(), "新增:" + advert);
        int i = advertService.addAdvert(advert);
        if (i > 0)
            Log.i(this.getClass(), "新增成功");
        else
            Log.i(this.getClass(), "新增失败");
        return "forward:/advert.htm";
    }


    /**
     * 设置禁用和启用
     *
     * @param id       广告id
     * @param isEnable 是否启用
     * @return 结果集
     */
    @RequestMapping("Using.do")
    @ResponseBody
    public String updateAdvert(@RequestParam("id") Integer id, @RequestParam("isEnable") boolean isEnable) {
        Log.i(this.getClass(), "更改广告状态id为：" + id + "状态为:" + isEnable);
        int i = advertService.updateAdvert(id, !isEnable);
        //操作是否成功
        boolean result;
        if (i > 0) {
            result = true;
        } else
            result = false;
        JSONObject jsonObject = new JSONObject();
        //启动还是禁用
        jsonObject.put("isEnable", !isEnable);
        jsonObject.put("result", result);
        return jsonObject.toJSONString();
    }

    @RequestMapping("adByConditions.do")
    @ResponseBody
    public String adByConditions(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "adTitle") String adTitle,
                                 @RequestParam(value = "adStarttime", required = false) String adStarttime,
                                 @RequestParam(value = "adEndtime", required = false) String adEndtime,
                                 @RequestParam(value = "isEnable", required = false, defaultValue = "-1") Integer isEnable) {
        Log.i(this.getClass(), "多条件查询  广告标题：" + adTitle + "  开始时间：" + adStarttime + "  结束时间：" + adEndtime + "  启用状态：" + isEnable);
        PageHelper.startPage(pageNum, 5);
        List<Advert> adverts = advertService.adByConditions(adTitle, adStarttime, adEndtime, isEnable);
        //将数据库获取到数据放入pageInfo里  在页面上获取 需要通过pageInfo.list获取
        PageInfo<Advert> pageInfo = new PageInfo<Advert>(adverts);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);

        //因为里面有date类型数据所以需要转换一下
        System.err.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }


    /**
     * 根据id删除
     *
     * @param id 集合是因为可以删除多条数据
     * @return json格式结果
     */
    @RequestMapping("delAdvert.do")
    @ResponseBody
    public String delAdvert(Integer[] id) {
        Log.i(this.getClass(), "删除广告的id：" + Arrays.toString(id));
        int i = advertService.delectAdvert(id);
        boolean isDel;
        if (i > 0)
            isDel = true;
        else
            isDel = false;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isDel", isDel);
        jsonObject.put("num", id);
        return jsonObject.toJSONString();
    }

}
