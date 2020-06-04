package com.mall.sms.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.sms.api.hystrix.SmsCouponFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wenguoli
 * @date 2019/12/18 11:13
 */
//@FeignClient(value = "mall-market-service", fallback = SmsCouponFeignApiHystrix.class, configuration = FeignConfig.class)
public interface SmsCouponFeignApi {
    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId  优惠券id
     * @param useStatus 0->未使用；1->已使用
     */
    @PutMapping("internal/update/{use_status}")
    void updateCouponStatus(@RequestParam("coupon_id") Long couponId, @PathVariable("use_status") Integer useStatus);
}
