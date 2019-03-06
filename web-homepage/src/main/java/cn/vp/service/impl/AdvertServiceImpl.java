package cn.vp.service.impl;

import cn.vp.bean.Advert;
import cn.vp.dao.AdvertMapper;
import cn.vp.service.AdvertService;
import cn.vp.util.Log;
import cn.vp.util.ResultBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertMapper advertMapper;


    @Override
    public String getAdvert() {
        ResultBean resultBean = new ResultBean();
        List<Advert> adverts = advertMapper.selectAdverts();
        if (null!=adverts&&adverts.size()>0){
            resultBean.setMessage("返回成功");
            resultBean.setResultCode("1000");
            resultBean.setDataInfo(adverts);
        }else {
            resultBean.setMessage("返回失败");
            resultBean.setResultCode("200");
        }
        return JSONObject.toJSONString(resultBean) ;
    }

}
