package cn.vp.bean;

/**
 * 市
 */
public class City {
    private String cityId;
    private String city;
    private String provinceId;

    @Override
    public String toString() {
        return "City{" +
                "cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", provinceId='" + provinceId + '\'' +
                '}';
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public City() {
    }


}
