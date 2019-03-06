package cn.vp.dao;

import cn.vp.bean.ProductImages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品图片
 */
@Mapper
public interface ProductImagesMapper {
    int deleteByPrimaryKey(Integer id);

    @Insert("<script>"+"insert into tb_product_images\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"productId != null\">\n" +
            "        product_id,\n" +
            "      </if>\n" +
            "      <if test=\"imageUrl != null\">\n" +
            "        image_url,\n" +
            "      </if>\n" +
            "      <if test=\"imageSort != null\">\n" +
            "        image_sort,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"productId != null\">\n" +
            "        #{productId,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"imageUrl != null\">\n" +
            "        #{imageUrl,jdbcType=VARCHAR},\n" +
            "      </if>\n" +
            "      <if test=\"imageSort != null\">\n" +
            "        #{imageSort,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "    </trim>"+"</script>")
    int insert(ProductImages record);

    int insertSelective(ProductImages record);

    ProductImages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductImages record);

    int updateByPrimaryKey(ProductImages record);

    @Select("select * from tb_product_images where product_id=#{id}")
    List<ProductImages> getImgByProId(Integer id);


    @Delete("delete from tb_product_images where product_id=#{id}")
    int deleteAllById(Integer id);
}