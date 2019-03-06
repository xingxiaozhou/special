package cn.vp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 */
public class Product implements Serializable {
    private Integer id;

    private String proTitle;

    private String proKeywords;

    private Integer categoryid;

    private String proDesc;

    private Integer proStatus;

    private Date createTime;

    private Integer proSort;

    private Integer stock;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", proTitle='" + proTitle + '\'' +
                ", proKeywords='" + proKeywords + '\'' +
                ", categoryid=" + categoryid +
                ", proDesc='" + proDesc + '\'' +
                ", proStatus=" + proStatus +
                ", createTime=" + createTime +
                ", proSort=" + proSort +
                ", stock=" + stock +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProTitle() {
        return proTitle;
    }

    public void setProTitle(String proTitle) {
        this.proTitle = proTitle == null ? null : proTitle.trim();
    }

    public String getProKeywords() {
        return proKeywords;
    }

    public void setProKeywords(String proKeywords) {
        this.proKeywords = proKeywords == null ? null : proKeywords.trim();
    }

    public Integer getCategory() {
        return categoryid;
    }

    public void setCategory(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc == null ? null : proDesc.trim();
    }

    public Integer getProStatus() {
        return proStatus;
    }

    public void setProStatus(Integer proStatus) {
        this.proStatus = proStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getProSort() {
        return proSort;
    }

    public void setProSort(Integer proSort) {
        this.proSort = proSort;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}