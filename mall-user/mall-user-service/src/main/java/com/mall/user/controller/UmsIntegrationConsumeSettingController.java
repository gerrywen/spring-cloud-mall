package com.mall.user.controller;

import com.mall.admin.model.UmsIntegrationConsumeSetting;
import com.mall.user.service.UmsIntegrationConsumeSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * program: spring-cloud-mall->UmsIntegrationConsumeSettingController
 * description: 积分消费设置
 * author: gerry
 * created: 2019-12-23 21:49
 **/
@RestController
@Api(value = "UmsIntegrationConsumeSettingController", tags = "UmsIntegration-用户积分设置")
@RequestMapping("integration/setting")
public class UmsIntegrationConsumeSettingController {

    @Autowired
    private UmsIntegrationConsumeSettingService umsIntegrationConsumeSettingService;

    @GetMapping("internal/info")
    @ApiOperation("获取积分消费设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),

    })
    public UmsIntegrationConsumeSetting getInfo() {
        return umsIntegrationConsumeSettingService.getUmsIntegrationConsumeSetting();
    }

}
