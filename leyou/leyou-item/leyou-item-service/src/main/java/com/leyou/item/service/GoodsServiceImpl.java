package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.*;
import com.leyou.item.pojo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StockMapper stockMapper;
    /**
     * 分页查询spu
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {
        SpuExample spuExample = new SpuExample();
        SpuExample.Criteria criteria = spuExample.createCriteria();
        if(StringUtils.isNotBlank(key)){
        criteria.andTitleLike("%"+key+"%");
        }
        if(saleable!=null){
        criteria.andSaleableEqualTo(saleable);
        }
        PageHelper.startPage(page,rows);
        List<Spu> spus = this.spuMapper.selectByExample(spuExample);
        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);
        //把一个list集合stream后map遍历,
        List<SpuBo> spuBos = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            //工具类copy,到另一个类里面
            BeanUtils.copyProperties(spu, spuBo);

            spuBo.setBname(brandMapper.selectByPrimaryKey(spuBo.getBrandId()).getName());
            List<String> names = this.categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(names, "-"));
            return spuBo;
        }).collect(Collectors.toList());
        return new PageResult<SpuBo>(spuPageInfo.getTotal(),spuBos);


    }

    /**
     * 添加商品
     * @param spuBo
     */
    @Override
    @Transactional
    public void saveGoods(SpuBo spuBo) {
        //新增spu
        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        this.spuMapper.insertSelective(spuBo);
        //新增spudetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        this.spuDetailMapper.insertSelective(spuDetail);
        saveSkuAndStock(spuBo);


    }

    private void saveSkuAndStock(SpuBo spuBo) {
        //新增sku
        List<Sku> skus = spuBo.getSkus();
        for (Sku sku : skus) {
            sku.setId(null);
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insertSelective(sku);
            //新增stock
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(11);
            this.stockMapper.insertSelective(stock);
        }
    }

    @Override
    public SpuDetail querySpudetailBySpuId(Long spuid) {
        SpuDetailExample spuDetailExample = new SpuDetailExample();
        SpuDetailExample.Criteria criteria = spuDetailExample.createCriteria();
        criteria.andSpuIdEqualTo(spuid);
        List<SpuDetail> spuDetails = this.spuDetailMapper.selectByExample(spuDetailExample);
        return spuDetails.get(0);
    }

    /**
     * 根据spuid查询所以的sku
     * @param spuId
     * @return
     */
    @Override
    public List<Sku> querySkusBySpuId(Long spuId) {
        SkuExample skuExample = new SkuExample();
        SkuExample.Criteria criteria = skuExample.createCriteria();
        criteria.andSpuIdEqualTo(spuId);
        return this.skuMapper.selectByExample(skuExample);
    }
@Transactional
    @Override
    public void updateGoods(SpuBo spuBo) {
        //删除sku与stock\
        //收集所有sku
        SkuExample skuExample = new SkuExample();
        SkuExample.Criteria criteria = skuExample.createCriteria();
        criteria.andSpuIdEqualTo(spuBo.getId());
        List<Sku> skus = this.skuMapper.selectByExample(skuExample);
        for (Sku sku : skus) {
          this.skuMapper.deleteByPrimaryKey(sku.getId());
            StockExample stockExample = new StockExample();
            StockExample.Criteria criteria1 = stockExample.createCriteria();
            criteria1.andSkuIdEqualTo(sku.getId());
            List<Stock> stocks = this.stockMapper.selectByExample(stockExample);
            for (Stock stock : stocks) {
                this.stockMapper.deleteByPrimaryKey(stock.getId());
            }

        }


        //新增sku与stock
        //新增sku
       this.saveSkuAndStock(spuBo);

        //更新spu,spudetail
        spuBo.setSaleable(null);
        spuBo.setValid(null);
        spuBo.setCreateTime(null);
        spuBo.setLastUpdateTime(new Date());
        this.spuMapper.updateByPrimaryKeySelective(spuBo);

        this.spuDetailMapper.updateByPrimaryKeySelective(spuBo.getSpuDetail());
    }
}
