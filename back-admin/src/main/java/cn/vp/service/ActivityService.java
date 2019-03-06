package cn.vp.service;

import cn.vp.bean.Activity;

import java.util.List;

/**
 * 活跃奖励
 */
public interface ActivityService {
    List<Activity> getActive();

    Activity getActivityById(Integer id);

    int activityAdd(Activity activity);

    int activityUpdate(Activity activity);

    int activityIsOpen(Integer id, Boolean isOpen);
}
