package com.leyou.search.client;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.api.GoodsApi;
import com.leyou.item.bo.SpuBo;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Repository
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {

//    @GetMapping("spu/page")
//    public PageResult<SpuBo> querySpuByPage(
//            @RequestParam(value = "key",required = false)String key,
//            @RequestParam(value = "saleable",required = false)Boolean saleable,
//            @RequestParam(value = "page",defaultValue = "1")Integer page,
//            @RequestParam(value = "rows",defaultValue = "5")Integer rows
//    );
}
