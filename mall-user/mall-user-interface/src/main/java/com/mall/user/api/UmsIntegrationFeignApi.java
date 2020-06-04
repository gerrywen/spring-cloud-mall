package com.mall.user.api;

import com.mall.admin.model.UmsIntegrationConsumeSetting;
import com.mall.common.base.config.FeignConfig;
import com.mall.user.api.hystrix.UmsIntegrationFeignApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * program: spring-cloud-mall->UmsIntegrationFeignApi
 * description: 用户积分
 * author: gerry
 * created: 2019-12-23 22:00
 **/
//@FeignClient(value = "mall-integration", fallback = UmsIntegrationFeignApiHystrix.class, configuration = FeignConfig.class)
public interface UmsIntegrationFeignApi {

    @GetMapping("integration/setting/internal/info")
    UmsIntegrationConsumeSetting getInfo();
}
