package cn.vp.dao;

import cn.vp.bean.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品信息
 */
@Mapper
public interface ProductMapper {

    @Select("SELECT * from tb_product where category_id=#{id} LIMIT 10")
    List<Product> getcateproducts(Integer id);

    @Select("select id from tb_category")
    List<Integer> getCategory();


}