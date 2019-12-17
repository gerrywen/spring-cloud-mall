package com.mall.seckill.client;

import com.mall.oms.api.OmsOrderApi;
import com.mall.seckill.config.OrderConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 98050
 * @Time: 2018-11-12 15:19
 * @Feature: 订单接口
 */
@FeignClient(value = "order-service",configuration = OrderConfig.class)
public interface OrderClient extends OmsOrderApi {
}
