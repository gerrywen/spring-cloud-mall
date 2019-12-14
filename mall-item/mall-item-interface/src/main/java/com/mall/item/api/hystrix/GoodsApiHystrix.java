package com.mall.item.api.hystrix;

import com.mall.item.api.GoodsApi;
import com.mall.item.pojo.Sku;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->GoodsApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-14 08:06
 **/
@Component
public class GoodsApiHystrix implements GoodsApi {
    @Override
    public Sku querySkuById(Long id) {
        return null;
    }
}
