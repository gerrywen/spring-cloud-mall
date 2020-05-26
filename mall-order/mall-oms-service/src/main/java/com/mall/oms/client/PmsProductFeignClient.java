package com.mall.oms.client;

import com.mall.item.api.PmsProductFeignApi;
import com.mall.item.api.PmsSkuStockFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 98050
 * Time: 2018-12-11 20:50
 * Feature:商品FeignClient
 */
@FeignClient(value = "mall-item-service")
public interface PmsProductFeignClient extends PmsProductFeignApi {
}
