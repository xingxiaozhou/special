package cn.vp.dao;

import cn.vp.bean.Coupon;
import cn.vp.bean.CouponType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * 查询所有优惠券
     * @return 优惠券集合
     */
    @Select("select t.* ,c.coupon_name from tb_coupon t,tb_coupontype c where t.coupon_type=c.id ORDER BY t.id")
    List<Coupon> getCoupons();


    @Select("select c.*,t.coupon_name from tb_coupon c,tb_coupontype t where c.id=#{id} and c.coupon_type=t.id ")
    Coupon getCouponById(Integer id);

    /**
     * 将优惠券添加到用户优惠券表
     * @return 是否成功
     */
    @Insert("<script>" + " insert into tb_coupon\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"couponCode != null\">\n" +
            "        coupon_code,\n" +
            "      </if>\n" +
            "      <if test=\"couponType != null\">\n" +
            "        coupon_type,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"couponCode != null\">\n" +
            "        #{couponCode},\n" +
            "      </if>\n" +
            "      <if test=\"couponType != null\">\n" +
            "        #{couponType},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    int addCoupon(Coupon coupon);


    @Select("select * from tb_coupontype")
    List<CouponType> getCouponType();

    @Update("<script> " + "   update tb_coupon\n" +
            "    <set>\n" +
            "      <if test=\"id != null\">\n" +
            "        id = #{id,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"couponCode != null\">\n" +
            "        coupon_code = #{couponCode},\n" +
            "      </if>\n" +
            "      <if test=\"couponType != null\">\n" +
            "        coupon_type = #{couponType},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + " </script> ")
    int couponUpdate(Coupon coupon);
}
