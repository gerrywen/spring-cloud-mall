package com.mall.user.api;

import com.mall.admin.model.UmsMember;
import com.mall.common.base.config.FeignConfig;
import com.mall.user.api.hystrix.UmsMemberApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * program: spring-cloud-mall->UmsMemberApi
 * description:
 * author: gerry
 * created: 2019-12-16 21:28
 **/
@FeignClient(value = "user-service", fallback = UmsMemberApiHystrix.class, configuration = FeignConfig.class)
public interface UmsMemberApi {
    /**
     * 根据会员id修改会员积分
     */
    @PutMapping("integration")
    void updateIntegration(@RequestParam("integration") Integer integration);


    /**
     * 根据会员编号获取会员
     */
    @GetMapping("info")
    UmsMember getUserInfo();
}
