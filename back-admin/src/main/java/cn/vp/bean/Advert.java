package cn.vp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告实体类
 */
public class Advert implements Serializable {
    private Integer id;

    private String adTitle;

    private String adImage;

    private String adUrl;

    private Date adStarttime;

    private Date adEndtime;

    private Boolean isEnable;

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", adTitle='" + adTitle + '\'' +
                ", adImage='" + adImage + '\'' +
                ", adUrl='" + adUrl + '\'' +
                ", adStarttime=" + adStarttime +
                ", adEndtime=" + adEndtime +
                ", isEnable=" + isEnable +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle == null ? null : adTitle.trim();
    }

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage == null ? null : adImage.trim();
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl == null ? null : adUrl.trim();
    }

    public Date getAdStarttime() {
        return adStarttime;
    }

    public void setAdStarttime(Date adStarttime) {
        this.adStarttime = adStarttime;
    }

    public Date getAdEndtime() {
        return adEndtime;
    }

    public void setAdEndtime(Date adEndtime) {
        this.adEndtime = adEndtime;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
}