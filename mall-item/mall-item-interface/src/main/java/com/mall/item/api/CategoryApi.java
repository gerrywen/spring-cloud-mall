package com.mall.item.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.item.api.hystrix.BrandApiHystrix;
import com.mall.item.api.hystrix.CategoryApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * program: spring-cloud-mall->CategoryApi
 * description: 分类接口
 * author: gerry
 * created: 2019-12-14 08:00
 **/
@FeignClient(value = "mall-item", fallback = CategoryApiHystrix.class, configuration = FeignConfig.class)
@RequestMapping("category")
public interface CategoryApi {
}
