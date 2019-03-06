package cn.vp.service;

import cn.vp.bean.Admin;

import java.util.List;

/**
 * 管理员登陆
 */
public interface AdminService {
    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     * @return 0失败 1成功
     */
    public Admin login(String username, String password);



    /**
     * 新增
     * @param admin 管理员
     * @return 返回操作的条数
     */
    public int addAdmin(Admin admin);



    /**
     * 查询所有
     * @return 菜单集合
     */
    public List<Admin> getAdmins();

    /**
     * 批量删除
     * @param ids 需要删除的id集合
     * @return 删除的条数
     */
    public int delectAdmin(Integer[] ids);

    int selectByName(String adminUser);

    /**
     * 修改
     * @param admin 管理员
     * @return 修改的条数
     */
    public int updateAdmin(Admin admin);


}
