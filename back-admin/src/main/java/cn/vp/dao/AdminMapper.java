package cn.vp.dao;

import cn.vp.bean.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 管理员Mapper
 */
@Mapper
public interface AdminMapper {


    @Delete(" delete from tb_admin where id = #{id,jdbcType=INTEGER}")
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into tb_admin (id, admin_user, admin_password, \n" +
            "      admin_email, admin_phone, create_time, \n" +
            "      is_superadmin)\n" +
            "    values (#{id,jdbcType=INTEGER}, #{adminUser,jdbcType=VARCHAR}, #{adminPassword,jdbcType=VARCHAR}, \n" +
            "      #{adminEmail,jdbcType=VARCHAR}, #{adminPhone,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, \n" +
            "      #{isSuperadmin,jdbcType=BIT})")
    int insert(Admin admin);


    @Insert("<script> " + " insert into tb_admin\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"adminUser != null\">\n" +
            "        admin_user,\n" +
            "      </if>\n" +
            "      <if test=\"adminPassword != null\">\n" +
            "        admin_password,\n" +
            "      </if>\n" +
            "      <if test=\"adminEmail != null\">\n" +
            "        admin_email,\n" +
            "      </if>\n" +
            "      <if test=\"adminPhone != null\">\n" +
            "        admin_phone,\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        create_time,\n" +
            "      </if>\n" +
            "      <if test=\"isSuperadmin != null\">\n" +
            "        is_superadmin,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"adminUser != null\">\n" +
            "        #{adminUser,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adminPassword != null\">\n" +
            "        #{adminPassword,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adminEmail != null\">\n" +
            "        #{adminEmail,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adminPhone != null\">\n" +
            "        #{adminPhone,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        #{createTime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"isSuperadmin != null\">\n" +
            "        #{isSuperadmin,jdbcType=BIT},\n" +
            "      </if>\n" +
            "    </trim>" + " </script> ")
    int insertSelective(Admin admin);


    @Select("  select * from tb_admin\n" +
            "    where id = #{id,jdbcType=INTEGER}")
    Admin selectByPrimaryKey(Integer id);

    @Update("<script>" + " update tb_admin\n" +
            "    <set>\n" +
            "      <if test=\"adminUser != null\">\n" +
            "        admin_user = #{adminUser,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adminPassword != null\">\n" +
            "        admin_password = #{adminPassword,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adminEmail != null\">\n" +
            "        admin_email = #{adminEmail,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"adminPhone != null\">\n" +
            "        admin_phone = #{adminPhone,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        create_time = #{createTime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"isSuperadmin != null\">\n" +
            "        is_superadmin = #{isSuperadmin,jdbcType=BIT},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + " </script> ")
    int updateByPrimaryKeySelective(Admin record);

    @Update(" update tb_admin\n" +
            "    set admin_user = #{adminUser,jdbcType=VARCHAR},\n" +
            "      admin_password = #{adminPassword,jdbcType=VARCHAR},\n" +
            "      admin_email = #{adminEmail,jdbcType=VARCHAR},\n" +
            "      admin_phone = #{adminPhone,jdbcType=VARCHAR},\n" +
            "      create_time = #{createTime,jdbcType=TIMESTAMP},\n" +
            "      is_superadmin = #{isSuperadmin,jdbcType=BIT}\n" +
            "    where id = #{id,jdbcType=INTEGER}")
    int updateByPrimaryKey(Admin admin);


    @Select("SELECT * FROM tb_admin WHERE admin_user=#{username} AND admin_password=#{password}")
    Admin selectLogin(@Param("username") String username, @Param("password") String password);


    @Select("SELECT * FROM tb_admin")
    List<Admin> selectAdmins();

    @Select("select count(*) from tb_admin where admin_user=#{adminUser}")
    int selectByName(String adminUser);


    @Delete("<script>" + "delete from tb_admin where id in" +
            "<foreach collection=\"array\" item=\"id\" open=\"(\" close=\")\" separator=\",\">\n" +
            "            #{id}\n" +
            "        </foreach>" + "</script>")
    int delectByIds(Integer[] ids);
}