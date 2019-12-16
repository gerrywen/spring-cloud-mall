package com.mall.oms.client;

import com.mall.user.api.UmsMemberReceiveAddressApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->UmsMemberReceiveAddressClient
 * description:
 * author: gerry
 * created: 2019-12-16 22:45
 **/
@FeignClient(value = "user-service")
public interface UmsMemberReceiveAddressClient extends UmsMemberReceiveAddressApi {
}
