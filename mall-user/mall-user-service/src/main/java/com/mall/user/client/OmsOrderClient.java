package com.mall.user.client;

import com.mall.oms.api.OmsOrderFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->OmsCartItemClient
 * description:
 * author: gerry
 * created: 2019-12-15 20:52
 **/
@FeignClient(value = "mall-order-service")
public interface OmsOrderClient extends OmsOrderFeignApi {
}
