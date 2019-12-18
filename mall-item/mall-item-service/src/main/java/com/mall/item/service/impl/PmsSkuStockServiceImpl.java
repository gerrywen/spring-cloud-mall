package com.mall.item.service.impl;

import com.mall.admin.mapper.PmsSkuStockMapper;
import com.mall.admin.model.PmsSkuStock;
import com.mall.item.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * program: spring-cloud-mall->PmsSkuStockServiceImpl
 * description:
 * author: gerry
 * created: 2019-12-18 22:10
 **/
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    @Autowired
    private PmsSkuStockMapper skuStockMapper;


    @Override
    public Integer lockStock(Long productSkuId, Integer quantity) {
        PmsSkuStock skuStock = skuStockMapper.selectByPrimaryKey(productSkuId);
        skuStock.setLockStock(skuStock.getLockStock() + quantity);
        return skuStockMapper.updateByPrimaryKeySelective(skuStock);
    }
}
