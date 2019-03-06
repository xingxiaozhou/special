package cn.vp.bean;

/**
 *  县
 */
public class Area {
    private String areaId;
    private String area;
    private String cityId;


    public Area() {
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId='" + areaId + '\'' +
                ", area='" + area + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
