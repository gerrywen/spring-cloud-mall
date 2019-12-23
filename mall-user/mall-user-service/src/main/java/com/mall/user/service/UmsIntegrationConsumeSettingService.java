package com.mall.user.service;

import com.mall.admin.model.UmsIntegrationConsumeSetting;

/**
 * program: spring-cloud-mall->UmsIntegrationConsumeSettingService
 * description: 积分消费设置
 * author: gerry
 * created: 2019-12-23 21:45
 **/
public interface UmsIntegrationConsumeSettingService {

    /**
     * 获取积分配置
     * @return
     */
    UmsIntegrationConsumeSetting getUmsIntegrationConsumeSetting();
}
