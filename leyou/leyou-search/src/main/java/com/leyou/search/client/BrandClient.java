package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

@Repository
@FeignClient("item-service")
public interface BrandClient extends BrandApi {

//    @GetMapping("spu/page")
//    public PageResult<SpuBo> querySpuByPage(
//            @RequestParam(value = "key",required = false)String key,
//            @RequestParam(value = "saleable",required = false)Boolean saleable,
//            @RequestParam(value = "page",defaultValue = "1")Integer page,
//            @RequestParam(value = "rows",defaultValue = "5")Integer rows
//    CategoryA);
}
