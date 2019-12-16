package com.mall.user.api;

import com.mall.admin.model.UmsMemberReceiveAddress;
import com.mall.common.base.config.FeignConfig;
import com.mall.user.api.hystrix.UmsMemberReceiveAddressApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * program: spring-cloud-mall->UmsMemberReceiveAddressApi
 * description:
 * author: gerry
 * created: 2019-12-16 22:28
 **/
@FeignClient(value = "user-service", fallback = UmsMemberReceiveAddressApiHystrix.class, configuration = FeignConfig.class)
public interface UmsMemberReceiveAddressApi {

    @GetMapping("member/address/list")
    List<UmsMemberReceiveAddress> list();

    @GetMapping("member/address/{id}")
    UmsMemberReceiveAddress getItem(Long id);
}
