package cn.vp.service.impl;

import cn.vp.bean.Category;
import cn.vp.dao.CategoryMapper;
import cn.vp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<Category> getCategory(Integer cateType) {
        return categoryMapper.getCategory(cateType);
    }

    @Override
    public int delCategory(Integer id) {
        return categoryMapper.delCategory(id);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<Category> getCategorys() {
        return categoryMapper.getCategorys();
    }
}
