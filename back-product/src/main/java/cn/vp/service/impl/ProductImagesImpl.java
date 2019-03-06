package cn.vp.service.impl;

import cn.vp.bean.Product;
import cn.vp.bean.ProductImages;
import cn.vp.dao.ProductImagesMapper;
import cn.vp.service.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 商品图片
 */
@Service
public class ProductImagesImpl implements ProductImagesService {

    @Autowired
    ProductImagesMapper productImagesMapper;

    @Override
    public int addImages(ProductImages productImages) {
        return productImagesMapper.insert(productImages);
    }

    @Override
    public List<ProductImages> getImgByProId(Integer id) {
        return productImagesMapper.getImgByProId(id);
    }

    /**
     * 根据商品id 删除该商品所有图片
     * @param id 商品id
     */
    @Override
    public int deleteAllById(Integer id) {
        return productImagesMapper.deleteAllById(id);
    }
}
