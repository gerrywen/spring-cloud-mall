package com.mall.oms.client;

import com.mall.item.api.PmsSkuStockFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->PmsSkuStockFeignClient
 * description:
 * author: gerry
 * created: 2019-12-24 11:06
 **/
@FeignClient(value = "mall-item-service")
public interface PmsSkuStockFeignClient extends PmsSkuStockFeignApi {
}
