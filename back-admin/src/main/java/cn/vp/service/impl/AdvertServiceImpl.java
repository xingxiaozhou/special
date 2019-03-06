package cn.vp.service.impl;

import cn.vp.bean.Advert;
import cn.vp.dao.AdvertMapper;
import cn.vp.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告实现类
 */
@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertMapper advertMapper;

    @Override
    public int addAdvert(Advert advert) {
        return advertMapper.insertSelective(advert);
    }

    @Override
    public Advert getAdvert(int id) {
        return advertMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Advert> getAdvert() {
        return advertMapper.selectAdverts();
    }

    @Override
    public int delectAdvert(Integer[] ids) {
        return advertMapper.delectByIds(ids);
    }

    @Override
    public int updateAdvert(Integer id, Boolean isEnable) {
        return advertMapper.updateByPrimaryKeySelective(id, isEnable);
    }

    @Override
    public List<Advert> adByConditions(String adTitle, String adStarttime,
                                       String adEndtime, Integer isEnable){
        return advertMapper.adByConditions(adTitle,adStarttime,adEndtime,isEnable);
    }
}
