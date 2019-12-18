package com.mall.sms.controller;

import com.mall.auth.entity.UserInfo;
import com.mall.common.base.response.Result;
import com.mall.sms.interceptor.LoginInterceptor;
import com.mall.sms.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenguoli
 * @date 2019/12/18 10:30
 */
@RestController
@Api(value = "SmsCouponController", tags = "marketing-优惠券")
@RequestMapping("coupon")
public class SmsCouponController {

    @Autowired
    private SmsCouponService smsCouponService;

    // 内部服务调用
    @PutMapping("internal/update/{use_status}")
    @ApiOperation("将优惠券信息更改为指定状态:0->未使用,1->已使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "coupon_id", value = "优惠券ID", required = true, dataType = "long", paramType = "query"),
    })
    public Result updateCouponStatus(@RequestParam("coupon_id") Long couponId, @PathVariable("use_status") Integer useStatus) {
        UserInfo loginUser = LoginInterceptor.getLoginUser();
        smsCouponService.updateCouponStatus(couponId, loginUser.getId(), useStatus);
        return Result.success("");
    }


}
