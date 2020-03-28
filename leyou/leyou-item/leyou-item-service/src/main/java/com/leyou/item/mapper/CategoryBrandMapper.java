package com.leyou.item.mapper;

import com.leyou.item.pojo.CategoryBrand;
import com.leyou.item.pojo.CategoryBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryBrandMapper {
    long countByExample(CategoryBrandExample example);

    int deleteByExample(CategoryBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryBrand record);

    int insertSelective(CategoryBrand record);

    List<CategoryBrand> selectByExample(CategoryBrandExample example);

    CategoryBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CategoryBrand record, @Param("example") CategoryBrandExample example);

    int updateByExample(@Param("record") CategoryBrand record, @Param("example") CategoryBrandExample example);

    int updateByPrimaryKeySelective(CategoryBrand record);

    int updateByPrimaryKey(CategoryBrand record);
}