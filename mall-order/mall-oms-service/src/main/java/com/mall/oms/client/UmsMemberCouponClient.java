package com.mall.oms.client;

import com.mall.user.api.UmsMemberCouponApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->UmsMemberCouponClient
 * description:
 * author: gerry
 * created: 2019-12-16 22:44
 **/
@FeignClient(value = "user-service")
public interface UmsMemberCouponClient extends UmsMemberCouponApi {
}
