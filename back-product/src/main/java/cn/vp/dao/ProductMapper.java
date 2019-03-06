package cn.vp.dao;

import cn.vp.bean.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


/**
 * 商品管理
 */
@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);


    @Insert("<script>" + " insert into tb_product\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"proTitle != null\">\n" +
            "        pro_title,\n" +
            "      </if>\n" +
            "      <if test=\"proKeywords != null\">\n" +
            "        pro_keywords,\n" +
            "      </if>\n" +
            "      <if test=\"category != null\">\n" +
            "        category_id,\n" +
            "      </if>\n" +
            "      <if test=\"proDesc != null\">\n" +
            "        pro_desc,\n" +
            "      </if>\n" +
            "      <if test=\"proStatus != null\">\n" +
            "        pro_status,\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        create_time,\n" +
            "      </if>\n" +
            "      <if test=\"proSort != null\">\n" +
            "        pro_sort,\n" +
            "      </if>\n" +
            "      <if test=\"stock != null\">\n" +
            "        stock,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"proTitle != null\">\n" +
            "        #{proTitle,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"proKeywords != null\">\n" +
            "        #{proKeywords,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"category != null\">\n" +
            "        #{category.id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"proDesc != null\">\n" +
            "        #{proDesc,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"proStatus != null\">\n" +
            "        #{proStatus,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        #{createTime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"proSort != null\">\n" +
            "        #{proSort,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"stock != null\">\n" +
            "        #{stock,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "    </trim>" + "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    @Select("select count(*) from tb_product where category_id=#{id}")
    int getCountProduct(Integer id);

    @Select("<script>" + "select * from tb_product" +
            "<where><if test=\"message!=null and message!=''\">" +
            "pro_title LIKE CONCAT('%',#{message},'%') OR pro_keywords LIKE CONCAT('%',#{message},'%') </if>" +
            "<if test=\"proStatus!=null and proStatus!='' \">" +
            "and pro_status=#{proStatus}</if>" +
            "<if test=\"categoryId!=null and categoryId!='' \">" +
            " and category_id=#{categoryId}</if></where>" + "</script>")
    @Results(id = "MapOneToOne", value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "pro_title", property = "proTitle"),
            @Result(column = "pro_keywords", property = "proKeywords"),
            @Result(column = "pro_desc", property = "proDesc"),
            @Result(column = "pro_status", property = "proStatus"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "pro_sort", property = "proSort"),
            @Result(column = "stock", property = "stock"),
            @Result(property = "category", column = "category_id",
                    one = @One(select = "cn.vp.dao.CategoryMapper.getCategoryById", fetchType = FetchType.EAGER))})
    List<Product> queryProductByConditions(@Param("message") String message,
                                           @Param("proStatus") Integer proStatus, @Param("categoryId") Integer categoryId);


    @Update("<script>" + "update tb_product\n" +
            "    <set>\n" +
            "      <if test=\"enable != null\">\n" +
            "        pro_status = #{enable},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}" + "</script>")
    int updateEnable(@Param("id") Integer id, @Param("enable") Integer enable);


    @Select("select * from tb_product where id=#{id}")
    @ResultMap("MapOneToOne")
    Product queryProductById(Integer id);

    @Update("<script>"+" update tb_product\n" +
            "    <set>\n" +
            "      <if test=\"proTitle != null\">\n" +
            "        pro_title = #{proTitle,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"proKeywords != null\">\n" +
            "        pro_keywords = #{proKeywords,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"category != null\">\n" +
            "        category_id = #{category.id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"proDesc != null\">\n" +
            "        pro_desc = #{proDesc,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"proStatus != null\">\n" +
            "        pro_status = #{proStatus,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"createTime != null\">\n" +
            "        create_time = #{createTime,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"proSort != null\">\n" +
            "        pro_sort = #{proSort,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"stock != null\">\n" +
            "        stock = #{stock,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "    </set>\n" +
            "    where id = #{id,jdbcType=INTEGER}"+"</script>")
    int updateProductById(Product product);

    @Delete("delete from tb_product where id=#{id}")
    int deleteProduct(Integer id);
}