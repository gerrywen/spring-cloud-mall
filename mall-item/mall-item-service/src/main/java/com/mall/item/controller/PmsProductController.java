package com.mall.item.controller;

import com.mall.item.service.PmsProductService;
import com.mall.item.vo.PmsCartProductVO;
import com.mall.item.vo.PmsPromotionProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * program: spring-cloud-mall->PmsProductController
 * description: 商品控制器
 * author: gerry
 * created: 2019-12-18 23:02
 **/
@RestController
@Api(value = "PmsHomeController", tags = "item-锁定库存")
@RequestMapping("product")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    @GetMapping("internal/cart/{id}")
    @ApiOperation(value = "获取购物车需要的商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticket", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "商品Id", required = true, dataType = "long", paramType = "query"),
    })
    public PmsCartProductVO getCartProduct(@PathVariable("id") Long id) {
        return pmsProductService.getCartProduct(id);
    }

    @GetMapping("internal/promotion")
    @ApiOperation(value = "获取促销商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticket", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "ids", value = "商品Ids", required = true, dataType = "array", paramType = "query"),
    })
    public List<PmsPromotionProductVO> getPromotionProductList(@RequestParam("ids") List<Long> ids) {
        return pmsProductService.getPromotionProductList(ids);
    }
}
