package com.leyou.item.mapper;

import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.pojo.SpuDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpuDetailMapper {
    long countByExample(SpuDetailExample example);

    int deleteByExample(SpuDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpuDetail record);

    int insertSelective(SpuDetail record);

    List<SpuDetail> selectByExample(SpuDetailExample example);

    SpuDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpuDetail record, @Param("example") SpuDetailExample example);

    int updateByExample(@Param("record") SpuDetail record, @Param("example") SpuDetailExample example);

    int updateByPrimaryKeySelective(SpuDetail record);

    int updateByPrimaryKey(SpuDetail record);
}