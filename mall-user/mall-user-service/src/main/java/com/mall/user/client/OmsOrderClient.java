package com.mall.user.client;

import com.mall.oms.api.OmsOrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->OmsCartItemClient
 * description:
 * author: gerry
 * created: 2019-12-15 20:52
 **/
@FeignClient(value = "order-service")
public interface OmsOrderClient extends OmsOrderApi {
}
