package cn.vp.service.Impl;

import cn.vp.bean.User;
import cn.vp.dao.UserMapper;
import cn.vp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User login(String userName, String passWord) {
        return userMapper.login(userName,passWord);
    }

    @Override
    public List<User> selectUsers() {
        return userMapper.selectAll();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int selecrUsername(String username) {
        return userMapper.selectUsername(username);
    }

    @Override
    public List<User> getUsers(String userName, String startCreateTime, String endCreateTime) {
        return userMapper.getUsers(userName,startCreateTime,endCreateTime);
    }

    @Override
    public int updateAdvert(Integer id, boolean status) {
        return userMapper.updateAdvert(id,status);
    }
}
