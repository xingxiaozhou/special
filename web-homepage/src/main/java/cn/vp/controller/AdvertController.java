package cn.vp.controller;

import cn.vp.bean.Advert;
import cn.vp.service.AdvertService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 广告控制器
 */
@Controller
public class AdvertController {

    @Autowired
    AdvertService advertService;


    @RequestMapping(value = "/api/v1/home/advert",method = RequestMethod.GET)
    @ResponseBody
    public String getAdvert(){
        Log.i(this.getClass(), "获取广告信息" );
        String json=advertService.getAdvert();


        //json对象转换成javabean对象
//        JSONObject jsonObject = JSON.parseObject(json);
//        JSONArray students = jsonObject.getJSONArray("dataInfo");
//        //遍历方式2
//        for (Object obj : students) {
//            JSONObject aa = (JSONObject) obj;
//            Advert people = (Advert) JSONObject.toJavaObject(aa, Advert.class);
//            System.out.println(people);
//        }

       return json;
    }



}
