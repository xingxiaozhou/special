package cn.vp.bean;

import java.io.Serializable;


/**
 * 菜单管理 
 */
public class Menu implements Serializable {
    private Integer id;

    private String menuName;

    private Integer parentId;

    private Boolean isHref;

    private String hrefUrl;

    private String menuIcon;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", isHref=" + isHref +
                ", hrefUrl='" + hrefUrl + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsHref() {
        return isHref;
    }

    public void setIsHref(Boolean isHref) {
        this.isHref = isHref;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl == null ? null : hrefUrl.trim();
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }
}