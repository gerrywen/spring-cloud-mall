package com.mall.user.service.impl;

import com.mall.admin.mapper.UmsIntegrationConsumeSettingMapper;
import com.mall.admin.model.UmsIntegrationConsumeSetting;
import com.mall.user.service.UmsIntegrationConsumeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * program: spring-cloud-mall->UmsIntegrationConsumeSettingServiceImpl
 * description:
 * author: gerry
 * created: 2019-12-23 21:46
 **/
@Service
public class UmsIntegrationConsumeSettingServiceImpl implements UmsIntegrationConsumeSettingService {

    /**
     * 获取用户积分配置
     */
    @Autowired
    private UmsIntegrationConsumeSettingMapper integrationConsumeSettingMapper;


    @Override
    public UmsIntegrationConsumeSetting getUmsIntegrationConsumeSetting() {
        return integrationConsumeSettingMapper.selectByPrimaryKey(1L);
    }
}
