package com.mall.oms.client;

import com.mall.common.base.config.FeignConfig;
import com.mall.user.api.UserApi;
import com.mall.user.api.hystrix.UserApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->UserClient
 * description: 用户消费
 * author: gerry
 * created: 2019-11-25 00:48
 **/
@FeignClient(value = "mall-user-service", fallback = UserApiHystrix.class, configuration = FeignConfig.class)
public interface UserClient extends UserApi {
}
