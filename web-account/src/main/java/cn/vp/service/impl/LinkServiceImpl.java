package cn.vp.service.impl;

import cn.vp.bean.Area;
import cn.vp.bean.City;
import cn.vp.bean.Province;
import cn.vp.dao.LinkMapper;
import cn.vp.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 三级联动
 */
@Service
public class LinkServiceImpl implements LinkService {


    @Autowired
    LinkMapper linkMapper;

    @Override
    public List<Province> getProvince() {
        return linkMapper.query();
    }

    @Override
    public List<City> getCity(String provincesid) {
        return linkMapper.queryCity(provincesid);
    }

    @Override
    public List<Area> getArea(String cityid) {
        return linkMapper.queryArea(cityid);
    }
}
