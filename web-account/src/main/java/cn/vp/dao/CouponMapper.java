package cn.vp.dao;

import cn.vp.bean.Activity;
import cn.vp.bean.Coupon;
import cn.vp.bean.UserCoupon;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 优惠券Mapper
 */
@Mapper
public interface CouponMapper {

    /**
     * 随机获取一条优惠券
     * @return
     */
    @Select("SELECT t1.*,c.coupon_name FROM\ttb_coupon AS t1,tb_coupontype c\n" +
            "JOIN ( SELECT\tROUND(RAND()*((\tSELECT\tMAX(id)\tFROM\ttb_coupon) - (\n" +
            "\t\t\t\t\tSELECT MIN(id) FROM tb_coupon)) + (\n" +
            "\t\t\t\tSELECT MIN(id) FROM tb_coupon)) AS id\n" +
            ") AS t2\n" +
            "WHERE\n" +
            "\tt1.id >= t2.id AND t1.coupon_type=c.id\n" +
            "ORDER BY\n" +
            "\tt1.id\n" +
            "LIMIT 1")
    Coupon getCoupon();

    /**
     * 将优惠券添加到用户优惠券表
     * @return 是否成功
     */
    @Insert("<script>" + " insert into tb_user_coupon\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        user_id,\n" +
            "      </if>\n" +
            "      <if test=\"coupontId != null\">\n" +
            "        coupont_id,\n" +
            "      </if>\n" +
            "      <if test=\"isuse != null\">\n" +
            "        isuse,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        #{userId},\n" +
            "      </if>\n" +
            "      <if test=\"coupontId != null\">\n" +
            "        #{coupontId},\n" +
            "      </if>\n" +
            "      <if test=\"isuse != null\">\n" +
            "        #{isuse},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    int saveUserCoupon(UserCoupon userCoupon);

    /**
     * 获取优惠券规则
     */
    @Select("SELECT * from tb_activity WHERE isopen=1")
    @Results(id = "MapOneToOne", value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "pro_keywords", property = "proKeywords"),
            @Result(column = "active_days", property = "activeDays"),
            @Result(column = "pro_status", property = "proStatus"),
            @Result(column = "isopen", property = "isopen"),
            @Result(property = "coupon", column = "coupon_id",
                    one = @One(select = "cn.vp.dao.CouponMapper.getCouponById", fetchType = FetchType.EAGER))})
    List<Activity> getActive();


    @Select("select c.*,t.coupon_name from tb_coupon c,tb_coupontype t where c.id=#{id} and c.coupon_type=t.id ")
    Coupon getCouponById(Integer id);

}
