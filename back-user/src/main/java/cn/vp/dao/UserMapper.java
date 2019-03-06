package cn.vp.dao;

import cn.vp.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户管理
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

    @Select("select * from tb_user")
    List<User> selectAll();

    @Select("select * from tb_user where id=#{id}")
    User selectById(Integer id);

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
}