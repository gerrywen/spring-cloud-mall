package com.mall.seckill.client;

import com.mall.oms.api.OmsOrderFeignApi;
import com.mall.seckill.config.OrderConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 98050
 * @Time: 2018-11-12 15:19
 * @Feature: 订单接口
 */
@FeignClient(value = "mall-order-service",configuration = OrderConfig.class)
public interface OmsOrderFeignClient extends OmsOrderFeignApi {
}
