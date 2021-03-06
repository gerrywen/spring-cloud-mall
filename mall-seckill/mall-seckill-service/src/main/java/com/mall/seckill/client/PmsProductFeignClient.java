package com.mall.seckill.client;

import com.mall.item.api.PmsProductFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 98050
 * Time: 2018-10-11 20:50
 * Feature:商品FeignClient
 */
@FeignClient(value = "mall-item-service")
public interface PmsProductFeignClient extends PmsProductFeignApi {
}
