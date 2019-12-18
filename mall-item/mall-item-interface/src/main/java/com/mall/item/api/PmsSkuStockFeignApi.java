package com.mall.item.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.item.api.hystrix.PmsSkuStockFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * program: spring-cloud-mall->PmsSkuStockFeignApi
 * description:
 * author: gerry
 * created: 2019-12-18 22:23
 **/
@FeignClient(value = "mall-item", fallback = PmsSkuStockFeignApiHystrix.class, configuration = FeignConfig.class)
public interface PmsSkuStockFeignApi {

    @PutMapping("sku_stock/internal/lock")
    Integer lockStock(@RequestParam("sku_id")  Long productSkuId, @RequestParam("quantity") Integer quantity);
}
