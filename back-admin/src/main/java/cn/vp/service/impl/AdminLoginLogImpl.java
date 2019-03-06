package cn.vp.service.impl;

import cn.vp.bean.AdminLoginLog;
import cn.vp.dao.AdminLoginLogMapper;
import cn.vp.service.AdminLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员登陆日志实现类
 */
@Service
public class AdminLoginLogImpl implements AdminLoginLogService {




    @Autowired
    AdminLoginLogMapper adminLoginLogMapper;
    @Override
    public int addAdminLoginLog(AdminLoginLog adminLoginLog) {
        return adminLoginLogMapper.insertSelective(adminLoginLog);
    }

    @Override
    public int deleteAdminLoginLog(Integer adminId) {
        return adminLoginLogMapper.deleteAdminLoginLog(adminId);
    }

    @Override
    public List<AdminLoginLog> selectAll() {
        return adminLoginLogMapper.selectAll();
    }

    @Override
    public List<AdminLoginLog> selectByNameAndTime(String adminUser, String startTime, String endTime) {
        return adminLoginLogMapper.selectByNameAndTime(adminUser,startTime,endTime);
    }
}
