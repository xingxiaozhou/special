package cn.vp.controller;

import cn.vp.bean.Product;
import cn.vp.service.ProductService;
import cn.vp.util.ResultBean;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页商品
 */
@Controller
public class CateproductController {

    @Autowired
    ProductService productService;


    /**
     * 商品首页列表接口
     * @return resultBean
     */
    @GetMapping("/api/v1/home/getcateproducts")
    @ResponseBody
    public String getcateproducts(){
        ResultBean resultBean=new ResultBean();
        //每个分类下获取10条商品。
        List<Product> productList = productService.getcateproducts();
        if (productList!=null&&productList.size()>0){
            resultBean.setResultCode("1000");
            resultBean.setMessage("成功");
            resultBean.setDataInfo(productList);
        }else {
            resultBean.setResultCode("200");
            resultBean.setMessage("失败");
        }
        return JSON.toJSONString(resultBean);
    }


}
