package com.mall.oms.client;

import com.mall.user.api.UmsIntegrationFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->UmsIntegrationFeignClient
 * description:
 * author: gerry
 * created: 2019-12-24 11:09
 **/
@FeignClient(value = "user-service")
public interface UmsIntegrationFeignClient extends UmsIntegrationFeignApi {
}
