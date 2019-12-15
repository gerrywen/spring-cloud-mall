package com.mall.oms.controller;

import com.mall.admin.model.OmsCartItem;
import com.mall.auth.entity.UserInfo;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.oms.interceptor.LoginInterceptor;
import com.mall.oms.service.OmsCartItemService;
import com.mall.oms.po.CartPromotionItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * program: spring-cloud-mall->OmsCartItemController
 * description:购物车管理Controller
 * author: gerry
 * created: 2019-12-15 20:41
 **/
@RestController
@Api(value = "OmsCartItemController", tags = "order-购物车管理")
@RequestMapping("cart")
public class OmsCartItemController {
    @Autowired
    private OmsCartItemService cartItemService;

    @PostMapping("add")
    @ApiOperation("添加商品到购物车")
    public Result add(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.add(cartItem);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.CART_ADD_ERROR);
    }

    @GetMapping("list")
    @ApiOperation("获取某个会员的购物车列表")
    public Result<List<OmsCartItem>> list() {
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        List<OmsCartItem> cartItemList = cartItemService.list(currentMember.getId());
        return Result.success(cartItemList);
    }

    @GetMapping("list/promotion")
    @ApiOperation("获取某个会员的购物车列表,包括促销信息")
    public Result<List<CartPromotionItem>> listPromotion() {
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(currentMember.getId());
        return Result.success(cartPromotionItemList);
    }

}
