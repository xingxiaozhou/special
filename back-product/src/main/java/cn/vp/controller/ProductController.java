package cn.vp.controller;

import cn.vp.bean.Category;
import cn.vp.bean.Product;
import cn.vp.bean.ProductImages;
import cn.vp.service.CategoryService;
import cn.vp.service.ProductImagesService;
import cn.vp.service.ProductService;
import cn.vp.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 商品管理
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductImagesService productImagesService;


    @RequestMapping("/queryCateTypes.do")
    public String product(HttpSession session) {
        Log.i(this.getClass(), "跳转到基本信息管理页面");
        List<Category> category = categoryService.getCategorys();
        session.setAttribute("categories", category);
        return "product";
    }


    @RequestMapping("product.htm")
    public String prod() {
        return "product";
    }

    @RequestMapping("productAdd.htm")
    public String prodAdd() {
        return "productAdd";
    }

    /**
     * 动态查询
     *
     * @param message    商品标题&关键词
     * @param proStatus  商品状态
     * @param categoryId 商品分类
     * @param pageNum    第几页
     * @return json
     */
    @RequestMapping("queryProductByConditions.do")
    @ResponseBody
    public String queryProductByConditions(@RequestParam(value = "message", required = false) String message,
                                           @RequestParam(required = false) Integer proStatus,
                                           @RequestParam(required = false) Integer categoryId,
                                           Integer pageNum) {
        Log.i(this.getClass(), "商品动态查询：message:" + message + "  proStatus:" + proStatus + "  categoryId:" + categoryId);
        PageHelper.startPage(pageNum, 5);
        List<Product> products = productService.queryProductByConditions(message, proStatus, categoryId);
        for (Product p : products
                ) {
            System.err.println(p);
        }
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return JSON.toJSONString(pageInfo, SerializerFeature.DisableCircularReferenceDetect);
    }


    /**
     * 新增商品
     *
     * @param product 商品信息
     * @return json
     */
    @RequestMapping("addProduct.do")
    @ResponseBody
    public String addProduct(Product product, String[] productImages) {
        product.setCreateTime(new Date());
        Log.i(this.getClass(), "新增商品：product-" + product);
        int i = productService.addProduct(product, productImages);
        if (i > 0) {
            return JSONObject.toJSONString("商品信息新增成功");
        }
        return JSONObject.toJSONString("新增失败");
    }


    /**
     * 上架 下架
     *
     * @param id     商品id
     * @param enable 上架还是下架
     * @return json
     */
    @RequestMapping("updateProStatus.do")
    @ResponseBody
    public String updateProStatus(Integer id, @RequestParam("enable") Integer enable) {
        Log.i(this.getClass(), "更改广告状态id为：" + id + "状态为:" + enable);
        int i = productService.updateEnable(id, enable);
        //操作是否成功
        if (i > 0) {
            return JSONObject.toJSONString("修改成功");
        } else
            return JSONObject.toJSONString("修改失败");
    }


    /**
     * 根据id查询商品然后跳转到
     *
     * @param model 存放数据
     * @param id    商品id
     * @return 跳转到修改页面
     */
    @RequestMapping("queryProductById.do")
    public String queryProductById(Model model, Integer id) {
        Log.i(this.getClass(), "");
        Product product = productService.queryProductById(id);
        List<ProductImages> productImagesList = productImagesService.getImgByProId(id);
        for (ProductImages p : productImagesList
                ) {
            System.err.println(p);
        }
        model.addAttribute("productImagesList", productImagesList);
        model.addAttribute("product", product);
        return "productUpdate";
    }


    /**
     * 修改商品
     *
     * @param product 商品
     * @return json
     */
    @RequestMapping("updateProductById.do")
    @ResponseBody
    public String updateProductById(Product product, String[] productImages) {
        Log.i(this.getClass(), "要修改的商品是：" + product);
        String message = productService.updateProductById(product, productImages);
        return JSONObject.toJSONString(message);
    }


    /**
     * 删除商品分类
     *
     * @param id 商品id
     * @return json
     */
    @RequestMapping("deleteProduct.do")
    @ResponseBody
    public String deleteProduct(Integer id) {
        Log.i(this.getClass(), "要删除的商品id" + id);
        JSONObject jsonObject = new JSONObject();
        int i = productService.deleteProduct(id);
        boolean isDel = false;
        if (i > 0) {
            isDel = true;
        }
        jsonObject.put("isDel", isDel);
        jsonObject.put("num", id);
        return jsonObject.toJSONString();
    }


}
