package cn.vp.service.Impl;

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
    public List<UserAddress> selectAll(String username) {
        return userAddressMapper.selectAll(username);
    }

    @Override
    public UserAddress select(Integer id, Integer userid) {
        return userAddressMapper.selectByPrimaryKey(id,userid);
    }
}
