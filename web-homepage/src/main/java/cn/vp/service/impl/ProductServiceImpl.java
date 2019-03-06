package cn.vp.service.impl;

import cn.vp.bean.Product;
import cn.vp.dao.ProductMapper;
import cn.vp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> getcateproducts() {
        List<Product> products=new ArrayList<>();
        //先查询商品类别 再通过类别查询
        List<Integer> category = productMapper.getCategory();
        System.err.println("qqqqqqq"+category);
        for (Integer c:category ) {
            System.err.println("ccccc"+c);
            List<Product> product=  productMapper.getcateproducts(c);
            if (product!=null){
                products.addAll(product);
            }
        }
        return products;
    }
}
