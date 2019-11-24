package com.mall.auth.client;

import com.mall.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->UserClient
 * description: 用户消费
 * author: gerry
 * created: 2019-11-25 00:48
 **/
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
