package cn.vp.service.impl;

import cn.vp.bean.Admin;
import cn.vp.dao.AdminMapper;
import cn.vp.service.AdminLoginLogService;
import cn.vp.service.AdminService;
import cn.vp.util.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户登陆
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminLoginLogService adminLoginLogService;

    public Admin login(String username, String password) {
        Log.i(this.getClass(), "登陆:username=" + username + "  password:" + password);
        return adminMapper.selectLogin(username, password);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.insertSelective(admin);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminMapper.selectAdmins();
    }

    @Override
    public int delectAdmin(Integer[] ids) {
        //删除管理员 要删除他的登陆记录
        for (int i = 0; i < ids.length; i++) {
            adminLoginLogService.deleteAdminLoginLog(ids[i]);
        }
        return adminMapper.delectByIds(ids);
    }

    @Override
    public int selectByName(String adminUser) {
        return adminMapper.selectByName(adminUser);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

}
