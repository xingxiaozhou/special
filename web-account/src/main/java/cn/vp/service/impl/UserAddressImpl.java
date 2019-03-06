package cn.vp.service.impl;

import cn.vp.bean.UserAddress;
import cn.vp.dao.UserAddressMapper;
import cn.vp.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址
 */
@Service
public class UserAddressImpl implements UserAddressService {

    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public int addAddress(UserAddress userAddress) {
        return userAddressMapper.insertSelective(userAddress);
    }

    @Override
    public int delectAddress(Integer id) {
        return userAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateAddress(UserAddress userAddress) {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Override
    public List<UserAddress> selectAll() {
        return userAddressMapper.selectAll();
    }

    @Override
    public UserAddress select(Integer id, Integer userid) {
        return null;
    }

    @Override
    public List<UserAddress> getAddressById(Integer userid) {
        return userAddressMapper.selectByPrimaryKey(userid);
    }

    @Override
    public UserAddress getAddress(Integer id) {
        return userAddressMapper.getAddress(id);
    }

}
