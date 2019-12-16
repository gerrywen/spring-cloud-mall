package com.mall.user.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.user.api.hystrix.UmsMemberCouponApiHystrix;
import com.mall.user.vo.SmsCouponHistoryDetailVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * program: spring-cloud-mall->UmsMemberCouponApi
 * description:
 * author: gerry
 * created: 2019-12-16 22:28
 **/
@FeignClient(value = "user-service", fallback = UmsMemberCouponApiHystrix.class, configuration = FeignConfig.class)
public interface UmsMemberCouponApi {

    @GetMapping(value = "member/coupon/list/cart/{type}")
    List<SmsCouponHistoryDetailVO> listCart(@PathVariable("type") Integer type);
}
