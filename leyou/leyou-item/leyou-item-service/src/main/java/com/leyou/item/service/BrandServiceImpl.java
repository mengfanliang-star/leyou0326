package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryBrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.BrandExample;
import com.leyou.item.pojo.CategoryBrand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;
    @Override
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        if(StringUtils.isNotBlank(key)){
        criteria.andNameLike("%"+key+"%");
        }
        BrandExample.Criteria criteria2 = brandExample.createCriteria();
        criteria2.andLetterEqualTo(key);
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(sortBy)){
            brandExample.setOrderByClause(sortBy+" "+(desc?"desc":"asc"));
        }
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        return new PageResult<>(brandPageInfo.getTotal(),brandPageInfo.getList());

    }
@Transactional
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        int i = this.brandMapper.insertSelective(brand);
        CategoryBrand categoryBrand = new CategoryBrand();
        categoryBrand.setBrandId(brand.getId());
        for (Long cid : cids) {
            categoryBrand.setCategoryId(cid);
            categoryBrandMapper.insertSelective(categoryBrand);
        }



    }
}
