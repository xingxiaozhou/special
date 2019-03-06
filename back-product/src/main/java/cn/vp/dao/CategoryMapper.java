package cn.vp.dao;

import cn.vp.bean.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品分类
 */
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    @Update("<script>" + " update tb_category\n" +
            "    <set>\n" +
            "      <if test=\"cateTitle != null\">\n" +
            "        cate_title = #{cateTitle,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"cateType != null\">\n" +
            "        cate_type = #{cateType,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"cateImg != null\">\n" +
            "        cate_img = #{cateImg,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"cateSort != null\">\n" +
            "        cate_sort = #{cateSort,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + "</script>")
    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);


    @Select("<script>" + "select * from tb_category " +
            "<where><if test=\"cateType!=null and cateType!='' \">cate_type=#{cateType}</if>" +
            "</where>" + "</script>")
    List<Category> getCategory(@Param("cateType") Integer cateType);

    @Delete("delete from tb_category where id=#{id}")
    int delCategory(Integer id);

    @Select("select * from tb_category where id=#{id}")
    Category getCategoryById(Integer id);

    @Select("select * from tb_category")
    List<Category> getCategorys();
}