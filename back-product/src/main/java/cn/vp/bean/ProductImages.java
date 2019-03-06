package cn.vp.bean;

import java.io.Serializable;

/**
 * 商品图片
 */
public class ProductImages implements Serializable {
    private Integer id;

    private Integer productId;

    private String imageUrl;

    private Integer imageSort;

    public ProductImages() {
    }

    @Override
    public String toString() {
        return "ProductImages{" +
                "id=" + id +
                ", productId=" + productId +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageSort=" + imageSort +
                '}';
    }

    public ProductImages(Integer productId, String imageUrl) {
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getImageSort() {
        return imageSort;
    }

    public void setImageSort(Integer imageSort) {
        this.imageSort = imageSort;
    }
}