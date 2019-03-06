package cn.vp.dao;

import cn.vp.bean.Activity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 活跃奖励管理
 */
@Mapper
public interface ActivityMapper {


    /**
     * 获取活跃奖励规则
     */
    @Select("SELECT * from tb_activity ")
    @Results(id = "MapOneToOne", value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "pro_keywords", property = "proKeywords"),
            @Result(column = "active_days", property = "activeDays"),
            @Result(column = "pro_status", property = "proStatus"),
            @Result(column = "isopen", property = "isopen"),
            @Result(property = "coupon", column = "coupon_id",
                    one = @One(select = "cn.vp.dao.CouponMapper.getCouponById", fetchType = FetchType.EAGER))})
    List<Activity> getActive();

    /**
     * 根据id获取
     *
     * @param id id
     * @return 活跃奖励规则
     */
    @Select("select * from tb_activity where id=#{id}")
    @ResultMap("MapOneToOne")
    Activity getActivityById(Integer id);

    /**
     * 新增活跃奖励规则
     *
     * @param activity 活跃奖励规则
     * @return 1成功 0失败
     */
    @Insert("<script>" + " insert into tb_activity\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"activityDays != null\">\n" +
            "        activity_days,\n" +
            "      </if>\n" +
            "      <if test=\"coupon != null\">\n" +
            "        coupon_id,\n" +
            "      </if>\n" +
            "      <if test=\"isopen != null\">\n" +
            "        isopen,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"activityDays != null\">\n" +
            "        #{activityDays},\n" +
            "      </if>\n" +
            "      <if test=\"coupon != null\">\n" +
            "        #{coupon.id},\n" +
            "      </if>\n" +
            "      <if test=\"isopen != null\">\n" +
            "        #{isopen},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    int activityAdd(Activity activity);


    /**
     * 修改活跃奖励规则
     * @param activity 活跃奖励规则
     * @return 1成功 0失败
     */
    @Update("<script> " + "update tb_activity\n" +
            "    <set>\n" +
            "      <if test=\"id != null\">\n" +
            "        id = #{id,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"activityDays != null\">\n" +
            "        activity_days = #{activityDays},\n" +
            "      </if>\n" +
            "      <if test=\"coupon != null\">\n" +
            "        coupon_id = #{coupon.id},\n" +
            "      </if>\n" +
            "      <if test=\"isopen != null\">\n" +
            "        isopen = #{isopen},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + " </script> ")
    int activityUpdate(Activity activity);


    /**
     * 开始或者关闭
     * @param id id
     * @param isOpen 是否开启
     * @return 1成功 0失败
     */
    @Update("<script>" + "update tb_activity\n" +
            "    <set>\n" +
            "      <if test=\"isOpen != null\">\n" +
            "        isopen = #{isOpen},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + "</script>")
    int updateIsOpen(@Param("id") Integer id, @Param("isOpen") Boolean isOpen);
}
