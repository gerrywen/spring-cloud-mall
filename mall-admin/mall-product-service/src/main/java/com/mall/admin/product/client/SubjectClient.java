package com.mall.admin.product.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * program: spring-cloud-mall->SubjectCilent
 * description:
 * author: gerry
 * created: 2019-12-02 19:55
 **/
@FeignClient(value = "mall-admin")
public interface SubjectClient {
}
