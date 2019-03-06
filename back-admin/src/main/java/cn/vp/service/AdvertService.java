package cn.vp.service;

import cn.vp.bean.Advert;

import java.util.List;

/**
 * 广告管理
 */
public interface AdvertService {

    /**
     * 新增
     *
     * @param advert 广告
     * @return 增加的条数
     */
    public int addAdvert(Advert advert);


    /**
     * 根据id获取广告
     *
     * @param id 广告id
     * @return 广告
     */
    public Advert getAdvert(int id);

    /**
     * 获取所有
     *
     * @return Advert集合
     */
    public List<Advert> getAdvert();


    /**
     * 根据id删除 是个数组 也可以是一个
     *
     * @param ids id数组
     * @return 删除的条数
     */
    public int delectAdvert(Integer[] ids);


    /**
     * 修改
     *
     * @param id       id
     * @param isEnable 启用还是禁用
     * @return 返回受影响的条数 0失败 1成功
     */
    public int updateAdvert(Integer id, Boolean isEnable);

    /**
     * 多条件查询
     * @param adTitle 广告标题
     * @param adStarttime 开始时间
     * @param adEndtime 结束时间
     * @param isEnable 是否禁用
     * @return 广告结果集
     */
    List<Advert> adByConditions(String adTitle, String adStarttime,
                                String adEndtime, Integer isEnable);
}
