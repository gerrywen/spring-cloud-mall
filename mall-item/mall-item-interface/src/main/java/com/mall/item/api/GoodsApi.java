package com.mall.item.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.item.api.hystrix.CategoryApiHystrix;
import com.mall.item.api.hystrix.GoodsApiHystrix;
import com.mall.item.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * program: spring-cloud-mall->GoodsApi
 * description:
 * author: gerry
 * created: 2019-12-14 08:02
 **/
@FeignClient(value = "mall-item", fallback = GoodsApiHystrix.class, configuration = FeignConfig.class)
@RequestMapping("goods")
public interface GoodsApi {

    /**
     * 根据sku的id查询sku
     * @param id
     * @return
     */
    @GetMapping("/sku/{id}")
    Sku querySkuById(@PathVariable("id") Long id);

}
