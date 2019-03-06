package cn.vp.dao;

import cn.vp.bean.User;
import cn.vp.bean.UserLogin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户信息
 */
@Mapper
public interface UserMapper {

    @Insert("<script>" + " insert into tb_user\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"userName != null\">\n" +
            "        user_name,\n" +
            "      </if>\n" +
            "      <if test=\"userPassword != null\">\n" +
            "        user_password,\n" +
            "      </if>\n" +
            "      <if test=\"userNickname != null\">\n" +
            "        user_nickname,\n" +
            "      </if>\n" +
            "      <if test=\"userHeadimg != null\">\n" +
            "        user_headimg,\n" +
            "      </if>\n" +
            "      <if test=\"userEmail != null\">\n" +
            "        user_email,\n" +
            "      </if>\n" +
            "      <if test=\"userPhone != null\">\n" +
            "        user_phone,\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        create_time,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"userName != null\">\n" +
            "        #{userName,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userPassword != null\">\n" +
            "        #{userPassword,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userNickname != null\">\n" +
            "        #{userNickname,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userHeadimg != null\">\n" +
            "        #{userHeadimg,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userEmail != null\">\n" +
            "        #{userEmail,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userPhone != null\">\n" +
            "        #{userPhone,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        #{createTime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    int insertSelective(User record);

    @Select("select *   from tb_user where user_name=#{username} and user_password=#{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @Select("select *   from tb_user where user_phone=#{userphone} and user_password=#{password}")
    User loginByPhone(@Param("userphone") String userphone, @Param("password") String password);

    @Select("select * from tb_user")
    List<User> selectAll();

    @Select("select count(*) from tb_user where user_name=#{username}")
    int selectUsername(String username);

    @Update("<script>" + "update tb_user\n" +
            "    <set>\n" +
            "      <if test=\"userName != null\">\n" +
            "        user_name = #{userName,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userPassword != null\">\n" +
            "        user_password = #{userPassword,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userNickname != null\">\n" +
            "        user_nickname = #{userNickname,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userHeadimg != null\">\n" +
            "        user_headimg = #{userHeadimg,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userEmail != null\">\n" +
            "        user_email = #{userEmail,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"userPhone != null\">\n" +
            "        user_phone = #{userPhone,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        create_time = #{createTime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + "</script>")
    int updateByPrimaryKeySelective(User record);


    @Select("<script>" + "select * from tb_user " +
            "<where><if test=\"userName!=null and userName!=''\">user_name=#{userName}</if>" +
            "<if test=\"startCreateTime!=null and startCreateTime!=''\">and create_time &gt;#{startCreateTime}</if>" +
            "<if test=\"endCreateTime!=null and endCreateTime!=''\">and create_time &lt;#{endCreateTime}</if>" +
            "</where>" + "</script>")
    List<User> getUsers(@Param("userName") String userName, @Param("startCreateTime") String startCreateTime, @Param("endCreateTime") String endCreateTime);


    @Update("update tb_user set user_status=#{status} where id=#{id}")
    int updateAdvert(@Param("id") Integer id, @Param("status") boolean status);

    @Select("select count(*) from tb_user where user_email=#{email}")
    int selectEmal(String email);

    @Update("update tb_user set user_password=#{userpassword} where user_email=#{userEmail}")
    int updateuserpwd(@Param("userEmail") String userEmail, @Param("userpassword") String userpassword);


    @Select("select * from tb_user_login where user_id=#{id}")
    UserLogin getLogin(Integer userid);


    @Insert("<script>" + " insert into tb_user_login\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        user_id,\n" +
            "      </if>\n" +
            "      <if test=\"loginDays != null\">\n" +
            "        login_days,\n" +
            "      </if>\n" +
            "      <if test=\"lastLoginDate != null\">\n" +
            "        last_login_date,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        #{userId},\n" +
            "      </if>\n" +
            "      <if test=\"loginDays != null\">\n" +
            "        #{loginDays},\n" +
            "      </if>\n" +
            "      <if test=\"lastLoginDate != null\">\n" +
            "        #{lastLoginDate},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    int addLogin(UserLogin userLogin);


    @Update("<script>" + "update tb_user_login\n" +
            "    <set>\n" +
            "      <if test=\"userId != null\">\n" +
            "        user_id = #{userId},\n" +
            "      </if>\n" +
            "      <if test=\"loginDays != null\">\n" +
            "        login_days = #{loginDays},\n" +
            "      </if>\n" +
            "      <if test=\"lastLoginDate != null\">\n" +
            "        last_login_date = #{lastLoginDate},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + "</script>")
    int updateLogin(UserLogin userLogin);

}