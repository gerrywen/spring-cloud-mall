package com.mall.seckill.client;

import com.mall.item.api.PmsProductApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 98050
 * Time: 2018-10-11 20:50
 * Feature:商品FeignClient
 */
@FeignClient(value = "item-service")
public interface GoodsClient extends PmsProductApi {
}
