package cn.vp.dao;

import cn.vp.bean.Area;
import cn.vp.bean.City;
import cn.vp.bean.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 三级联动
 */
@Mapper
public interface LinkMapper {

    //查询省
    @Select("select * FROM provinces")
    public List<Province> query();


    //根据省查询市

    @Select("select * FROM cities where provinceid=#{provincesid}")
    public List<City> queryCity(String provincesid);

    //根据市查询县
    @Select("select * FROM areas  where cityid=#{cityid}")
    public List<Area> queryArea(String cityid);


}
