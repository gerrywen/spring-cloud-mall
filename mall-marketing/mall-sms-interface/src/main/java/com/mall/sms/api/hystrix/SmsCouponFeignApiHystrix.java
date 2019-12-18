package com.mall.sms.api.hystrix;

import com.mall.sms.api.SmsCouponFeignApi;
import org.springframework.stereotype.Component;

/**
 * @author wenguoli
 * @date 2019/12/18 11:14
 */
@Component
public class SmsCouponFeignApiHystrix implements SmsCouponFeignApi {
    @Override
    public void updateCouponStatus(Long couponId, Integer useStatus) {

    }
}
