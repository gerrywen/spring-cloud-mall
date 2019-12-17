package com.mall.oms.controller;

import com.mall.admin.model.OmsCartItem;
import com.mall.auth.entity.UserInfo;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.oms.interceptor.LoginInterceptor;
import com.mall.oms.service.OmsCartItemService;
import com.mall.oms.po.CartPromotionItem;
import com.mall.oms.vo.CartProductVO;
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

    @ApiOperation("修改购物车中某个商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    @ResponseBody
    public Result updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        int count = cartItemService.updateQuantity(id, currentMember.getId(), quantity);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.CART_EDIT_QUANTITY_ERROR);
    }

    @ApiOperation("获取购物车中某个商品的规格,用于重选规格")
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public Result<CartProductVO> getCartProduct(@PathVariable Long productId) {
        CartProductVO cartProduct = cartItemService.getCartProduct(productId);
        return Result.success(cartProduct);
    }

    @ApiOperation("修改购物车中商品的规格")
    @RequestMapping(value = "/update/attr", method = RequestMethod.POST)
    @ResponseBody
    public Result updateAttr(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.updateAttr(cartItem);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.CART_EDIT_SPEC_ERROR);
    }

    @ApiOperation("删除购物车中的某个商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam("ids") List<Long> ids) {
        int count = cartItemService.delete(LoginInterceptor.getLoginUser().getId(), ids);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.CART_DELETE_ERROR);
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public Result clear() {
        int count = cartItemService.clear(LoginInterceptor.getLoginUser().getId());
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.CART_CLEAR_ERROR);
    }

}
