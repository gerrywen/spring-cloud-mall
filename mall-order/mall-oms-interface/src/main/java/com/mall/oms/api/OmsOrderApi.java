package com.mall.oms.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.common.base.response.Result;
import com.mall.oms.api.hystrix.OmsOrderApiHystrix;
import com.mall.oms.po.CartPromotionItem;
import com.mall.oms.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * program: spring-cloud-mall->OrderApi
 * description:
 * author: gerry
 * created: 2019-12-14 08:13
 **/
@FeignClient(value = "mall-item", fallback = OmsOrderApiHystrix.class, configuration = FeignConfig.class)
public interface OmsOrderApi {
    /**
     * 创建订单
     * @param seck
     * @param order
     * @return
     */
    @PostMapping("order")
    Result<List<Long>> createOrder(@RequestParam("seck") String seck, @RequestBody @Valid Order order);


    /**
     * 修改订单状态
     * @param id
     * @param status
     * @return
     */
    @PutMapping("order/{id}/{status}")
    Result<Boolean> updateOrderStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status);


    @GetMapping("cart/list/promotion")
    Result<List<CartPromotionItem>> listPromotion();
}
