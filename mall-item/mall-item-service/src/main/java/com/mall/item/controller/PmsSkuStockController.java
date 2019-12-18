package com.mall.item.controller;

import com.mall.item.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * program: spring-cloud-mall->PmsSkuStockController
 * description: 锁库存
 * author: gerry
 * created: 2019-12-18 22:15
 **/
@RestController
@Api(value = "PmsHomeController", tags = "item-锁定库存")
@RequestMapping("sku_stock")
public class PmsSkuStockController {

    @Autowired
    private PmsSkuStockService pmsSkuStockService;

    @PutMapping(value = "internal/lock")
    @ApiOperation(value = "锁定下单商品的所有库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticket", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "sku_id", value = "商品skuId", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "quantity", value = "购买数量", required = true, dataType = "int", paramType = "query"),
    })
    public Integer lockStock(@RequestParam("sku_id")  Long productSkuId, @RequestParam("quantity") Integer quantity) {
        return pmsSkuStockService.lockStock(productSkuId, quantity);
    }
}
