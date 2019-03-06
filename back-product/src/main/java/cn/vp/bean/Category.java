package cn.vp.bean;

import java.io.Serializable;

/**
 * 商品分类
 */
public class Category implements Serializable {
    private Integer id;

    private String cateTitle;

    private Integer cateType;

    private String cateImg;

    private Integer cateSort;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", cateTitle='" + cateTitle + '\'' +
                ", cateType=" + cateType +
                ", cateImg='" + cateImg + '\'' +
                ", cateSort=" + cateSort +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateTitle() {
        return cateTitle;
    }

    public void setCateTitle(String cateTitle) {
        this.cateTitle = cateTitle == null ? null : cateTitle.trim();
    }

    public Integer getCateType() {
        return cateType;
    }

    public void setCateType(Integer cateType) {
        this.cateType = cateType;
    }

    public String getCateImg() {
        return cateImg;
    }

    public void setCateImg(String cateImg) {
        this.cateImg = cateImg == null ? null : cateImg.trim();
    }

    public Integer getCateSort() {
        return cateSort;
    }

    public void setCateSort(Integer cateSort) {
        this.cateSort = cateSort;
    }
}