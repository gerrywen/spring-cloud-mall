package com.mall.oms.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.common.base.response.Result;
import com.mall.oms.api.hystrix.OmsCartItemApiHystrix;
import com.mall.oms.po.CartPromotionItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * program: spring-cloud-mall->OmsCartItemApi
 * description:
 * author: gerry
 * created: 2019-12-15 20:47
 **/
@FeignClient(value = "mall-order", fallback = OmsCartItemApiHystrix.class, configuration = FeignConfig.class)
public interface OmsCartItemApi {

    @GetMapping("cart/list/promotion")
    Result<List<CartPromotionItem>> listPromotion();
}
