package com.mall.oms.client;

import com.mall.sms.api.SmsCouponFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wenguoli
 * @date 2019/12/18 11:22
 */
@FeignClient(value = "mall-market-service")
public interface SmsMarketingFeignClient extends SmsCouponFeignApi {
}
