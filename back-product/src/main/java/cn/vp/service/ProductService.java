package cn.vp.service;


import cn.vp.bean.Product;

import java.util.List;

/**
 * 商品管理
 */
public interface ProductService {

    //根据类型id获取商品数量
    int getCountProduct(Integer id);


    //多条件查询
    List<Product> queryProductByConditions(String message, Integer proStatus, Integer categoryId);


    //新增
    int addProduct(Product product,String[] productImages);

    //上架下架
    int updateEnable(Integer id, Integer enable);

    //根据id查询商品
    Product queryProductById(Integer id);

    //修改商品
    String updateProductById(Product product,String[] productImages);

    //根据id删除
    int deleteProduct(Integer id);
}
