package cn.vp.service;

import cn.vp.bean.User;

import java.util.List;

/**
 * 用户管理类
 */
public interface UserService {

    /**
     * 新增用户
     * @return 成功返回1 不成功返回0
     */
     String addUser(User user);

    /**
     * 登陆
     * @return 成功返回对象
     */
    String login(String username, String password);


    /**
     * 查询所有用户
     * @return 返回所有用户
     */
    List<User> selectUsers();

    /**
     *  修改
     * @return 成功返回1 不成功返回0
     */
    int updateUser(User user);

    /**
     * 注册时 查看是否有存在用户名
     * @param username 用户名
     * @return 如果存在返回1 不存在返回0
     */
    int selecrUsername(String username);

    /**
     * 获取用户信息列表
     * @param userName
     * @param startCreateTime
     * @param endCreateTime
     * @return
     */
    List<User> getUsers(String userName, String startCreateTime, String endCreateTime);


    int updateAdvert(Integer id, boolean status);

    int selectEmal(String email);

    String updateuserpwd(String userEmail, String userpassword);
}
