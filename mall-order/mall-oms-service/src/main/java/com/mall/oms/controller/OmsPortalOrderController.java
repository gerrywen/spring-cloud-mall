package com.mall.oms.controller;

import com.mall.common.base.response.Result;
import com.mall.oms.dto.OrderParamDTO;
import com.mall.oms.po.ConfirmOrderResult;
import com.mall.oms.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理Controller
 * Created by macro on 2018/8/30.
 */
@RestController
@Api(value = "OmsPortalOrderController", tags = "order-订单管理")
@RequestMapping("order")
public class OmsPortalOrderController {
    @Autowired
    private OmsPortalOrderService portalOrderService;

    @ApiOperation("根据购物车信息生成确认单信息")
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public Result<ConfirmOrderResult> generateConfirmOrder() {
        ConfirmOrderResult confirmOrderResult = portalOrderService.generateConfirmOrder();
        return Result.success(confirmOrderResult);
    }

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public Object generateOrder(@RequestBody OrderParamDTO orderParam) {
        return portalOrderService.generateOrder(orderParam);
    }

    @ApiOperation("支付成功的回调")
    @RequestMapping(value = "/pay_success", method = RequestMethod.POST)
    @ResponseBody
    public Object paySuccess(@RequestParam Long orderId) {
        return portalOrderService.paySuccess(orderId);
    }

    @ApiOperation("自动取消超时订单")
    @RequestMapping(value = "/cancel_timeout", method = RequestMethod.POST)
    @ResponseBody
    public Object cancelTimeOutOrder() {
        return portalOrderService.cancelTimeOutOrder();
    }

    @ApiOperation("取消单个超时订单")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Result cancelOrder(Long orderId) {
        portalOrderService.sendDelayMessageCancelOrder(orderId);
        return Result.success("");
    }
}
