package com.mall.item.api.hystrix;

import com.mall.common.base.response.Result;
import com.mall.item.api.PmsSkuStockFeignApi;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->PmsSkuStockFeignApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-18 22:23
 **/
@Component
public class PmsSkuStockFeignApiHystrix implements PmsSkuStockFeignApi {
    @Override
    public Integer lockStock(Long productSkuId, Integer quantity) {
        return 0;
    }
}
