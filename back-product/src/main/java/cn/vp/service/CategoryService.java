package cn.vp.service;

import cn.vp.bean.Category;

import java.util.List;

/**
 * 商品分类管理
 */
public interface CategoryService {

    //获取该类型的所有数据
    List<Category> getCategory(Integer cateType);

    //根据id删除
    int delCategory(Integer id);

    //根据id获取
    Category getCategoryById(Integer id);

    //修改
    int updateCategory(Category category);

    //获取所有
    List<Category> getCategorys();
}
