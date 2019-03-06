package cn.vp.controller;

import cn.vp.bean.Menu;
import cn.vp.service.MenuService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 */
@Controller
@SessionAttributes("parentMenu")
public class MenuController implements ServletContextAware {

    ServletContext servletContext;

    @Autowired
    private MenuService menuService;


    @RequestMapping("/query")
    public String list() {
        System.err.println("/query");
        return "list";
    }

    @RequestMapping("/parentMenu.do")
    public String menu(Map<String, Object> map) {
        List<Menu> parentMenu = menuService.getParentMenu();
        map.put("parentMenu", parentMenu);
        return "menu";
    }


    /**
     * 获取菜单集合
     * @param pageNum 页码
     * @return json
     */
    @RequestMapping("/menus.do")
    @ResponseBody
    public String getMenus(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        //分页 now 页码  pagesize 显示几条
        Log.i(this.getClass(),"获取菜单集合 页码为："+pageNum);
        PageHelper.startPage(pageNum, 5);
        List<Menu> menus = menuService.getMenus();
        //将数据库获取到数据放入pageInfo里  在jsp页面上获取 需要通过pageInfo.list获取
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menus);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        //因为里面有date类型数据所以需要转换一下
        return JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 跳转到修改或者详情
     *
     * @param id     菜单id
     * @param method 1是详情 0是修改
     * @return 跳转到的页面
     */
    @RequestMapping("menu.do")
    public String addOrEditmenu(Map<String, Object> map, @Param("id") Integer id, @RequestParam(value = "method", required = false, defaultValue = "0") Integer method) {
        System.err.println("menu.do  method:"+method);
        Menu menu = menuService.getMenu(id);
        map.put("menu", menu);
        if (method == 1){
            Log.i(this.getClass(),"菜单跳转到详情页面 ");
            return "menuShow";}
        else{
            Log.i(this.getClass(),"菜单跳转到修改页面 ");
            return "menuHandle";
    }}



    /**
     * 照片上传和回显
     * <p>
     * //     * @param fileImage 图片名称
     *
     * @return json类型的对象
     */
//    @RequestMapping("/imgPath.do")
//    @ResponseBody
//    public String imageUpload(@RequestParam(name = "imageDemo",required = false) MultipartFile imageDemo) {
//        System.err.println("fileImage:"+imageDemo);
////        CommonsMultipartFile fileImages=(CommonsMultipartFile)fileImage;
//        // 获取上传图片的位置
//        String path = servletContext.getRealPath("/");
//        System.out.println("上传的路径为:" + path);
//        // 获取文件名称
//        String name = imageDemo.getOriginalFilename();
//        // 创建file对象 写入
//        File file = new File(path, name);
//        try {
//            imageDemo.transferTo(file);
////            fileImage.getFileItem().write(file);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        // 将上传的图片路径以json的方式返回客户端
////        String imagePath = "upload/" + name;
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("imagePath", name);
//        // 将对象转为json格式
//        String json = jsonObject.toJSONString();
//        System.out.println("json:" + json);
//        return json;
//    }


    /**
     * 跳转页面
     * @return 新增页面
     */
    @RequestMapping("/menuHandle.htm")
    public String menuHandle() {
        return "menuHandle";
    }

    /**
     * 添加菜单
     * @param menu 菜单
     * @return 跳转到父页面
     */
    @RequestMapping("menuAdd.do")
    public String menuAdd(Menu menu) {
        int i = menuService.addMenu(menu);
        System.err.println("新增" + i);
        return "forward:/parentMenu.do";
    }

    /**
     * 删除菜单
     * @param id id
     * @return json结果集
     */
    @RequestMapping("delMenu")
    @ResponseBody
    public String delMenu(Integer[] id) {
        for (Integer i:id
             ) {
            Log.i(this.getClass(),"删除菜单的id为 "+i);
        }
        int i = menuService.delectMenu(id);
        boolean isDel = false;
        if (i > 0) {
            isDel = true;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isDel", isDel);
        jsonObject.put("num", id);
        return jsonObject.toJSONString();
    }

    /**
     * 更新菜单
     * @param menu 菜单
     * @return 跳转页面
     */
    @RequestMapping("menuUpdate.do")
    public String menuUpdate(Menu menu) {
        int i = menuService.updateMenu(menu);
        Log.i(this.getClass(),"修改的菜单为： "+menu);
        if (i > 0) {
            return "menu";
        } else {
            return null;
        }
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
