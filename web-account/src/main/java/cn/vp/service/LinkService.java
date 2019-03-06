package cn.vp.service;

import cn.vp.bean.Area;
import cn.vp.bean.City;
import cn.vp.bean.Province;

import java.util.List;

/**
 * 三级联动
 */
public interface LinkService {

    public List<Province> getProvince();


    public List<City> getCity(String provincesid);

    public List<Area>getArea(String cityid);

}
