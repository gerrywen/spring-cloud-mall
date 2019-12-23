package com.mall.user.api.hystrix;

import com.mall.admin.model.UmsIntegrationConsumeSetting;
import com.mall.user.api.UmsIntegrationFeignApi;

/**
 * program: spring-cloud-mall->UmsIntegrationFeignApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-23 22:01
 **/
public class UmsIntegrationFeignApiHystrix implements UmsIntegrationFeignApi {
    @Override
    public UmsIntegrationConsumeSetting getInfo() {
        return new UmsIntegrationConsumeSetting();
    }
}
