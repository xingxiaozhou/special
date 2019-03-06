package cn.vp.dao;

import cn.vp.bean.AdminLoginLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 管理员登陆记录Mapper
 */
@Mapper
public interface AdminLoginLogMapper {

    @Insert("<script>" + " insert into tb_admin_login_log\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"adminId != null\">\n" +
            "        admin_id,\n" +
            "      </if>\n" +
            "      <if test=\"adminUser != null\">\n" +
            "        admin_user,\n" +
            "      </if>\n" +
            "      <if test=\"loginTime != null\">\n" +
            "        login_time,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"adminId != null\">\n" +
            "        #{adminId,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"adminUser != null\">\n" +
            "        #{adminUser,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"loginTime != null\">\n" +
            "        #{loginTime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    int insertSelective(AdminLoginLog record);


    @Select(" select * from tb_admin_login_log where id = #{id,jdbcType=INTEGER}")
    AdminLoginLog selectByPrimaryKey(Integer id);

    /**
     * 查询所有
     *
     * @return 管理员登陆记录
     */
    @Select("select * from tb_admin_login_log")
    List<AdminLoginLog> selectAll();


    @Select("<script>" + "SELECT * FROM tb_admin_login_log\n" +
            "    <where>\n" +
            "      <if test=\"adminUser!=null and adminUser!=''\">\n" +
            "        admin_user=#{adminUser}\n" +
            "      </if>\n" +
            "      <if test=\"startTime!=null and startTime!=''\">\n" +
            "        and login_time &gt;=#{startTime}\n" +
            "      </if>\n" +
            "      <if test=\"endTime!=null and endTime!=''\">\n" +
            "        AND login_time &lt;=#{endTime}\n" +
            "      </if>\n" +
            "    </where>" + "</script>")
    List<AdminLoginLog> selectByNameAndTime(@Param("adminUser") String adminUser, @Param("startTime") String startTime, @Param("endTime") String endTime);


    @Delete("delete  from tb_admin_login_log where admin_id=#{admin_id}")
    int deleteAdminLoginLog(Integer admin_id);

}