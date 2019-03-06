package cn.vp.dao;

import cn.vp.bean.Advert;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 广告管理
 */

@Mapper
public interface AdvertMapper {
    @Delete("  delete from tb_advert\n" +
            "    where id = #{id,jdbcType=INTEGER}")
    int deleteByPrimaryKey(Integer id);

    @Insert(" insert into tb_advert (id, ad_title, ad_image, \n" +
            "      ad_url, ad_starttime, ad_endtime, \n" +
            "      is_enable)\n" +
            "    values (#{id,jdbcType=INTEGER}, #{adTitle,jdbcType=VARCHAR}, #{adImage,jdbcType=VARCHAR}, \n" +
            "      #{adUrl,jdbcType=VARCHAR}, #{adStarttime,jdbcType=TIMESTAMP}, #{adEndtime,jdbcType=TIMESTAMP}, \n" +
            "      #{isEnable,jdbcType=BIT})")
    int insert(Advert record);

    @Insert("<script>" + "insert into tb_advert\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"adTitle != null\">\n" +
            "        ad_title,\n" +
            "      </if>\n" +
            "      <if test=\"adImage != null\">\n" +
            "        ad_image,\n" +
            "      </if>\n" +
            "      <if test=\"adUrl != null\">\n" +
            "        ad_url,\n" +
            "      </if>\n" +
            "      <if test=\"adStarttime != null\">\n" +
            "        ad_starttime,\n" +
            "      </if>\n" +
            "      <if test=\"adEndtime != null\">\n" +
            "        ad_endtime,\n" +
            "      </if>\n" +
            "      <if test=\"isEnable != null\">\n" +
            "        is_enable,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"adTitle != null\">\n" +
            "        #{adTitle,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adImage != null\">\n" +
            "        #{adImage,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adUrl != null\">\n" +
            "        #{adUrl,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adStarttime != null\">\n" +
            "        #{adStarttime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"adEndtime != null\">\n" +
            "        #{adEndtime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"isEnable != null\">\n" +
            "        #{isEnable,jdbcType=BIT},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    int insertSelective(Advert record);


    @Select(" select * from tb_advert\n" +
            "    where id = #{id,jdbcType=INTEGER}")
    Advert selectByPrimaryKey(Integer id);

    @Update("<script>" + "update tb_advert\n" +
            "    <set>\n" +
            "      <if test=\"isEnable != null\">\n" +
            "        is_enable = #{isEnable,jdbcType=BIT},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + "</script>")
    int updateByPrimaryKeySelective(@Param("id") Integer id, @Param("isEnable") Boolean isEnable);

    @Update(" update tb_advert\n" +
            "    set ad_title = #{adTitle,jdbcType=VARCHAR},\n" +
            "      ad_image = #{adImage,jdbcType=VARCHAR},\n" +
            "      ad_url = #{adUrl,jdbcType=VARCHAR},\n" +
            "      ad_starttime = #{adStarttime,jdbcType=TIMESTAMP},\n" +
            "      ad_endtime = #{adEndtime,jdbcType=TIMESTAMP},\n" +
            "      is_enable = #{isEnable,jdbcType=BIT}\n" +
            "    where id = #{id,jdbcType=INTEGER}")
    int updateByPrimaryKey(Advert record);

    @Select("select * from tb_advert")
    List<Advert> selectAdverts();

    @Delete("<script>" + "delete from tb_advert where id in" +
            "<foreach collection=\"array\" item=\"id\" open=\"(\" close=\")\" separator=\",\">\n" +
            "            #{id}\n" +
            "        </foreach>" + "</script>")
    int delectByIds(Integer[] ids);


    /**
     * 多条件查询
     * @param adTitle 广告标题
     * @param adStarttime 开始时间
     * @param adEndtime 结束时间
     * @param isEnable 是否禁用
     * @return 广告结果集
     */
    @Select("<script>"+" select * from tb_advert\n" +
            "    <where>\n" +
            "      <if test=\"adTitle!=null and adTitle!=''\">\n" +
            "        ad_title=#{adTitle}\n" +
            "      </if>\n" +
            "      <if test=\"adStarttime!=null and adStarttime!=''\">\n" +
            "        AND ad_starttime &gt;=#{adStarttime}\n" +
            "      </if>\n" +
            "      <if test=\"adEndtime!=null and adEndtime!=''\">\n" +
            "        AND  ad_endtime  &lt;=#{adEndtime}\n" +
            "      </if>\n" +
            "      <if test=\"isEnable!=null and isEnable!='-1'\">\n" +
            "        AND  is_enable=#{isEnable}\n" +
            "      </if>\n" +
            "    </where>"+"</script>")
    List<Advert> adByConditions(@Param("adTitle") String adTitle, @Param("adStarttime") String adStarttime,
                                @Param("adEndtime") String adEndtime, @Param("isEnable") Integer isEnable);
}