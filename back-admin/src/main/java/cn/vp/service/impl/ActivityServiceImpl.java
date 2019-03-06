package cn.vp.service.impl;

import cn.vp.bean.Activity;
import cn.vp.dao.ActivityMapper;
import cn.vp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活跃奖励
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    /**
     * 获取活跃奖励规则
     * @return 列表
     */
    @Override
    public List<Activity> getActive() {
        return activityMapper.getActive();
    }

    /**
     * 根据id获取活跃奖励
     * @param id id
     * @return 活跃奖励规则
     */
    @Override
    public Activity getActivityById(Integer id) {
        return activityMapper.getActivityById(id);
    }

    /**
     * 新增活跃奖励规则
     * @param activity 活跃奖励规则
     * @return 1成功 0失败
     */
    @Override
    public int activityAdd(Activity activity) {
        return activityMapper.activityAdd(activity);
    }

    /**
     * 修改活跃奖励规则
     * @param activity 活跃奖励规则
     * @return 1成功 0失败
     */
    @Override
    public int activityUpdate(Activity activity) {
        return activityMapper.activityUpdate(activity);
    }

    /**
     * 开启或关闭活跃奖励规则
     * @param id id
     * @param isOpen 状态
     * @return 1成功 0失败
     */
    @Override
    public int activityIsOpen(Integer id, Boolean isOpen) {
        return activityMapper.updateIsOpen(id,isOpen);
    }
}
