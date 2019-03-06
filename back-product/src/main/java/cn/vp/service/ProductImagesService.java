package cn.vp.service;


import cn.vp.bean.ProductImages;

import java.util.List;

/**
 * 商品图片
 */
public interface ProductImagesService {

    int addImages(ProductImages images);

    List<ProductImages> getImgByProId(Integer id);


    int deleteAllById(Integer id);
}
