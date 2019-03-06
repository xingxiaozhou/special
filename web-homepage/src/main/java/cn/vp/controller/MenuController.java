package cn.vp.controller;

import cn.vp.bean.Menu;
import cn.vp.service.MenuService;
import cn.vp.util.ResultBean;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜单
 */
@Controller
public class MenuController {

    @Autowired
    MenuService menuService;


    @RequestMapping("/home")
    public String getNum(){
        return "Number1";
    }

    @RequestMapping("/")
    public String getPage(){
        return "Number1";
    }

    //获取首页菜单
    @GetMapping("/api/v1/home/category")
    @ResponseBody
    public String getMenu(){
        ResultBean resultBean =new ResultBean();
        List<Menu> menus = menuService.getMenus();
        if (menus!=null&&menus.size()>0){
            //成功
            resultBean.setResultCode("1000");
            resultBean.setMessage("成功");
            resultBean.setDataInfo(menus);
        }else {
            //失败
            resultBean.setMessage("失败");
            resultBean.setResultCode("200");
        }
        return JSON.toJSONString(resultBean);
    }

}
