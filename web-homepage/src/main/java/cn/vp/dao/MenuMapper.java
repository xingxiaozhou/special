package cn.vp.dao;

import cn.vp.bean.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 菜单
 */
@Mapper
public interface MenuMapper {


    @Delete(" delete from tb_menu where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    @Delete("<script>" + "delete from tb_menu where id in" +
            " <foreach collection=\"array\" item=\"id\" open=\"(\" close=\")\" separator=\",\">\n" +
            "            #{id}\n" +
            "        </foreach>" + "</script>")
    int delectByIds(Integer[] ids);


    @Insert("insert into tb_menu ( menu_name, parent_id, is_href, href_url, menu_icon )" +
            " values ( #{menuName}, #{parentId}, #{isHref}, #{hrefUrl}, #{menuIcon})")
    int insert(Menu record);

    @Insert("<script> " + "insert into tb_menu\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"menuName != null\">\n" +
            "        menu_name,\n" +
            "      </if>\n" +
            "      <if test=\"parentId != null\">\n" +
            "        parent_id,\n" +
            "      </if>\n" +
            "      <if test=\"isHref != null\">\n" +
            "        is_href,\n" +
            "      </if>\n" +
            "      <if test=\"hrefUrl != null\">\n" +
            "        href_url,\n" +
            "      </if>\n" +
            "      <if test=\"menuIcon != null\">\n" +
            "        menu_icon,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"menuName != null\">\n" +
            "        #{menuName,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"parentId != null\">\n" +
            "        #{parentId,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"isHref != null\">\n" +
            "        #{isHref,jdbcType=BIT},\n" +
            "      </if>\n" +
            "      <if test=\"hrefUrl != null\">\n" +
            "        #{hrefUrl,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"menuIcon != null\">\n" +
            "        #{menuIcon,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "    </trim>" + " </script> "
    )
    int insertSelective(Menu record);


    @Select("  select \n" +
            "    *" +
            "    from tb_menu\n" +
            "    where id = #{id,jdbcType=INTEGER}")
    Menu selectByPrimaryKey(Integer id);

    @Update("<script> " + "   update tb_menu\n" +
            "    <set>\n" +
            "      <if test=\"menuName != null\">\n" +
            "        menu_name = #{menuName,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"parentId != null\">\n" +
            "        parent_id = #{parentId,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"isHref != null\">\n" +
            "        is_href = #{isHref,jdbcType=BIT},\n" +
            "      </if>\n" +
            "      <if test=\"hrefUrl != null\">\n" +
            "        href_url = #{hrefUrl,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"menuIcon != null\">\n" +
            "        menu_icon = #{menuIcon,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + " </script> ")
    int updateByPrimaryKeySelective(Menu record);

    @Update(" update tb_menu\n" +
            "    set menu_name = #{menuName,jdbcType=VARCHAR},\n" +
            "      parent_id = #{parentId,jdbcType=INTEGER},\n" +
            "      is_href = #{isHref,jdbcType=BIT},\n" +
            "      href_url = #{hrefUrl,jdbcType=VARCHAR},\n" +
            "      menu_icon = #{menuIcon,jdbcType=VARCHAR}\n" +
            "    where id = #{id,jdbcType=INTEGER}")
    int updateByPrimaryKey(Menu record);


    @Select("select * from tb_menu")
    List<Menu> selectMenus();

    /**
     * 查询所有父级别菜单
     * @return 父级菜单
     */
    @Select("select * from tb_menu where parent_id=0 and is_href=0")
    List<Menu> getParentMenu();
}