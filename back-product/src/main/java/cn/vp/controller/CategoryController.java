package cn.vp.controller;

import cn.vp.bean.Category;
import cn.vp.service.CategoryService;
import cn.vp.service.ProductService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 分类管理
 */
@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;


    //跳转到页面
    @RequestMapping("category.htm")
    public String category() {
        return "category";
    }


    /**
     * 获取商品分类列表
     *
     * @param cateType 分类类别
     * @return json
     */
    @RequestMapping("getCategory.do")
    @ResponseBody
    public String getCategory(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "cateType", required = false) Integer cateType) {
        Log.i(this.getClass(), "获取商品分类列表多条件查询  分类类别：" + cateType);
        PageHelper.startPage(pageNum, 20);
        List<Category> categories = categoryService.getCategory(cateType);
        //将数据库获取到数据放入pageInfo里  在页面上获取 需要通过pageInfo.list获取
        PageInfo<Category> pageInfo = new PageInfo<Category>(categories);
        //转成json返回出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo", pageInfo);
        System.err.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }


    /**
     * 删除商品分类
     *
     * @param id 商品分类id
     * @return json
     */
    @RequestMapping("delCategory.do")
    @ResponseBody
    public String delCategory(Integer id) {
        Log.i(this.getClass(), "要删除的商品分类的id" + id);
        JSONObject jsonObject = new JSONObject();
        //是否有
        if (productService.getCountProduct(id) > 0) {
            jsonObject.put("Product", true);
        } else {
            int i = categoryService.delCategory(id);
            boolean isDel = false;
            if (i > 0) {
                isDel = true;
            }

            jsonObject.put("isDel", isDel);
            jsonObject.put("num", id);
        }
        return jsonObject.toJSONString();
    }


    /**
     * 跳转到修改页面
     *
     * @param id 菜单id
     * @return 跳转到的页面
     */
    @RequestMapping("categoryHandle.do")
    public String categoryHandle(Map<String, Object> map, @Param("id") Integer id) {
        Log.i(this.getClass(), "跳转到商品分类修改页面");
        //先根据id查询数据
        Category category = categoryService.getCategoryById(id);
        map.put("category", category);
        return "categoryHandle";
    }

    /**
     * 修改商品类别
     *
     * @param category 要修改的值
     * @return json
     */
    @RequestMapping("/editCategory.do")
    public String editCategory(Category category) {
        Log.i(this.getClass(), "修改商品分类：" + category);
        int i = categoryService.updateCategory(category);
        if (i > 0) {
            return "category";
        } else {
            return "category";
        }
    }


}
