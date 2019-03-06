package cn.vp.dao;

import cn.vp.bean.UserAddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户收货地址
 */
@Mapper
public interface UserAddressMapper {

    @Delete(" delete from tb_user_address  where id = #{id,jdbcType=INTEGER}")
    int deleteByPrimaryKey(Integer id);

    @Insert("<script>"+"insert into tb_user_address\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        user_id,\n" +
            "      </if>\n" +
            "      <if test=\"addrProvince != null\">\n" +
            "        addr_province,\n" +
            "      </if>\n" +
            "      <if test=\"addrCity != null\">\n" +
            "        addr_city,\n" +
            "      </if>\n" +
            "      <if test=\"addrArea != null\">\n" +
            "        addr_area,\n" +
            "      </if>\n" +
            "      <if test=\"addrAddress != null\">\n" +
            "        addr_address,\n" +
            "      </if>\n" +
            "      <if test=\"recipientsName != null\">\n" +
            "        recipients_name,\n" +
            "      </if>\n" +
            "      <if test=\"recipientsPhone != null\">\n" +
            "        recipients_phone,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        #{userId,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"addrProvince != null\">\n" +
            "        #{addrProvince,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"addrCity != null\">\n" +
            "        #{addrCity,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"addrArea != null\">\n" +
            "        #{addrArea,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"addrAddress != null\">\n" +
            "        #{addrAddress,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"recipientsName != null\">\n" +
            "        #{recipientsName,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"recipientsPhone != null\">\n" +
            "        #{recipientsPhone,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "    </trim>"+"</script>")
    int insertSelective(UserAddress record);

    @Select("select * from tb_user_address where user_id=#{userId}")
    List<UserAddress> selectByPrimaryKey( @Param("userId") Integer userId);

    @Select("select * from tb_user_address")
    List<UserAddress> selectAll();

    @Update("<script>"+"  update tb_user_address\n" +
            "    <set>\n" +
            "      <if test=\"userId != null\">\n" +
            "        user_id = #{userId,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"addrProvince != null\">\n" +
            "        addr_province = #{addrProvince,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"addrCity != null\">\n" +
            "        addr_city = #{addrCity,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"addrArea != null\">\n" +
            "        addr_area = #{addrArea,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"addrAddress != null\">\n" +
            "        addr_address = #{addrAddress,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"recipientsPhone != null\">\n" +
            "        recipients_phone = #{recipientsPhone,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"recipientsName != null\">\n" +
            "        recipients_name = #{recipientsName,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}"+"</script>")
    int updateByPrimaryKeySelective(UserAddress record);


    @Select("select * from tb_user_address where id=#{id}")
    UserAddress getAddress(Integer id);
}