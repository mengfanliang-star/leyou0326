package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> queryCategoriesByPid(Long pid);
    /**
     * 根据分类id集合,查询名称集合.
     * @param ids
     * @return
     */
    List<String> queryNamesByIds(List<Long> ids);
}
