package com.mall.oms.client;

import com.mall.user.api.UmsMemberFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->UmsMemberClient
 * description:
 * author: gerry
 * created: 2019-12-16 21:28
 **/
@FeignClient(value = "user-service")
public interface UmsUserFeignClient extends UmsMemberFeignApi {
}
