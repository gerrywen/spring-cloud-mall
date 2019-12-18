package com.mall.item.service;

/**
 * program: spring-cloud-mall->PmsSkuStockService
 * description: 锁定库存
 * author: gerry
 * created: 2019-12-18 22:10
 **/
public interface PmsSkuStockService {
    /**
     * 锁定下单商品的所有库存
     * @param productSkuId
     * @param quantity
     */
    Integer lockStock(Long productSkuId, Integer quantity);
}
