package cn.vp.dao;

import cn.vp.bean.OrderComment;

public interface OrderCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderComment record);

    int insertSelective(OrderComment record);

    OrderComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderComment record);

    int updateByPrimaryKey(OrderComment record);
}