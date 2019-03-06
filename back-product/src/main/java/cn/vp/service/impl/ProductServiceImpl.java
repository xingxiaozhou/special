package cn.vp.service.impl;

import cn.vp.bean.Product;
import cn.vp.bean.ProductImages;
import cn.vp.dao.ProductMapper;
import cn.vp.service.ProductImagesService;
import cn.vp.service.ProductService;
import cn.vp.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductImagesService productImagesService;


    @Override
    public int getCountProduct(Integer id) {
        return productMapper.getCountProduct(id);
    }

    @Override
    public List<Product> queryProductByConditions(String message, Integer proStatus, Integer categoryId) {
        return productMapper.queryProductByConditions(message, proStatus, categoryId);
    }

    /**
     * 新增商品
     *
     * @param product       商品
     * @param productImages 商品图片
     * @return 返回值
     */
    @Override
    public int addProduct(Product product, String[] productImages) {
        //先将product添加到数据库 就会获得product id
        int i = productMapper.insertSelective(product);
        if (i > 0 && productImages != null && productImages.length > 0) {
            //添加成功
            for (int i1 = 0; i1 < productImages.length; i1++) {
                System.err.println(product);
                Log.i(this.getClass(), "添加商品图片：" + productImages[i1] + "  商品信息：" + product);
                // TODO: 2018/5/17 0017  暂时不知道排序的作用 就给赋值null
                ProductImages productImages1 = new ProductImages(product.getId(), productImages[i1]);
                productImagesService.addImages(productImages1);
            }
        }
        return i;
    }

    @Override
    public int updateEnable(Integer id, Integer enable) {
        //数据库是int 类型 1代表上架 2代表下架
        if (enable == 1)
            enable = 2;
        else
            enable = 1;
        return productMapper.updateEnable(id, enable);
    }

    @Override
    public Product queryProductById(Integer id) {
        return productMapper.queryProductById(id);
    }

    /**
     * 修改商品
     *
     * @param product       商品
     * @param productImages 商品id
     * @return
     */
    @Override
    public String updateProductById(Product product, String[] productImages) {
        int i = productMapper.updateProductById(product);
        // TODO: 2018/5/17 0017 暂时删除全部图片然后重新添加 后续再优化
        productImagesService.deleteAllById(product.getId());
        if (productImages != null && productImages.length > 0) {
            for (int i1 = 0; i1 < productImages.length; i1++) {
                Log.i(this.getClass(), "添加商品图片：" + productImages[i1] + "  商品信息：" + product);
                // TODO: 2018/5/17 0017  暂时不知道排序的作用 就给赋值null
                ProductImages productImages1 = new ProductImages(product.getId(), productImages[i1]);
                productImagesService.addImages(productImages1);
            }
        }
        if (i > 0) {
            return "商品信息修改成功";
        }
        return "修改失败";

    }

    @Override
    public int deleteProduct(Integer id) {
        return productMapper.deleteProduct(id);
    }
}
