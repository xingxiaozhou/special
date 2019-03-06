package cn.vp.service;

import cn.vp.bean.UserAddress;

import java.util.List;


/**
 * 收货地址管理
 */
public interface UserAddressService {

    /**
     * 新增地址
     * @param userAddress 收货地址
     * @return 0表示新增失败 1表示成功
     */
    int addAddress(UserAddress userAddress);


    /**
     * 删除
     * @param id 根据id删除
     * @return 0失败 1成功
     */
    int delectAddress(Integer id);

    /**
     * 修改
     * @param userAddress 收货地址
     * @return 0失败 1成功
     */
    int updateAddress(UserAddress userAddress);


    /**
     * 查询所有
     * @return 返回地址集合
     */
    List<UserAddress> selectAll(String username);

    /**
     *  根据id 和用户id查询
     * @param id 地址id
     * @param userid 用户id
     * @return 收货地址
     */
    UserAddress select(Integer id, Integer userid);

}
