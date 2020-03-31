package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> queryCategoriesByPid(Long pid) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andParentIdEqualTo(pid);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        return categories;

    }

    /**
     * 根据分类id集合,查询名称集合.
     * @param ids
     * @return
     */
    @Override
    public List<String> queryNamesByIds(List<Long> ids) {
        List<String> names = new ArrayList<>();
        for (Long id : ids) {
            Category category = this.categoryMapper.selectByPrimaryKey(id);
            names.add(category.getName());
        }

        return names;
    }
}
