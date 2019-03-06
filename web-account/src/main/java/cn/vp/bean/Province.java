package cn.vp.bean;

/**
 * 省
 */
public class Province {
   private String provinceId;

    private String province;


    public Province() {
    }

    public Province(String provinceId, String province) {
        this.provinceId = provinceId;
        this.province = province;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceId='" + provinceId + '\'' +
                ", province='" + province + '\'' +
                '}';
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
