package com.mall.user.controller;

import com.mall.admin.model.SmsCouponHistory;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.oms.po.CartPromotionItem;
import com.mall.user.client.OmsCartItemClient;
import com.mall.user.service.UmsMemberCouponService;
import com.mall.user.vo.SmsCouponHistoryDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户优惠券管理Controller
 * Created by macro on 2018/8/29.
 */
@RestController
@Api(value = "UmsMemberCouponController", tags = "UmsMember-用户优惠券管理")
@RequestMapping("member/coupon")
public class UmsMemberCouponController {
    @Autowired
    private UmsMemberCouponService memberCouponService;

    @Autowired
    private OmsCartItemClient cartItemClient;


    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{coupon_id}", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@PathVariable("coupon_id") Long couponId) {
        return memberCouponService.add(couponId);
    }

    @GetMapping("list")
    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    public Result<List<SmsCouponHistory>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistory> couponHistoryList = memberCouponService.list(useStatus);
        return Result.success(couponHistoryList);
    }

    @GetMapping(value = "list/cart/{type}")
    @ApiOperation("获取登录会员购物车的相关优惠券")
//    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
//            defaultValue = "1", allowableValues = "0,1", paramType = "query", dataType = "integer")
    public Result<List<SmsCouponHistoryDetailVO>> listCart(@PathVariable("type") Integer type) {
        Result<List<CartPromotionItem>> listResult = cartItemClient.listPromotion();
        //查询结果为空，则直接返回null
        if (listResult == null){
            return Result.error(CodeMsg.COUPON_GET_LIST_ERROR);
        }
        List<CartPromotionItem> cartPromotionItemList = listResult.getData();

        List<SmsCouponHistoryDetailVO> couponHistoryList = memberCouponService.listCart(cartPromotionItemList, type);
        return Result.success(couponHistoryList);
    }
}
