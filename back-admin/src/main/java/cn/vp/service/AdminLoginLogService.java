package cn.vp.service;

import cn.vp.bean.AdminLoginLog;

import java.util.List;

/**
 * 管理员登陆记录
 */
public interface AdminLoginLogService {


    /**
     * 新增一条记录
     * @param adminLoginLog 管理员登陆记录
     * @return 0表示失败 1表示成功
     */
    public int addAdminLoginLog(AdminLoginLog adminLoginLog);


    public int deleteAdminLoginLog(Integer adminId);


    /**
     * 查询所有
     * @return 登陆记录集合
     */
    public List<AdminLoginLog> selectAll();

    /**
     * 多条件查询 根据用户名 时间段查询
     * @param adminUser 用户名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 登陆记录集合
     */
    public List<AdminLoginLog> selectByNameAndTime(String adminUser, String startTime, String endTime);




}
