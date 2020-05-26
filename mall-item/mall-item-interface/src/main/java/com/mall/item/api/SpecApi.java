package com.mall.item.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.item.api.hystrix.SpecApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * program: spring-cloud-mall->SpecApi
 * description:
 * author: gerry
 * created: 2019-12-14 20:36
 **/
@FeignClient(value = "mall-item-service", fallback = SpecApiHystrix.class, configuration = FeignConfig.class)
public interface SpecApi {
    /**
     * 查询商品分类对应的规格参数模板
     * @param id
     * @return
     */
    @GetMapping("spec/{id}")
    ResponseEntity<String> querySpecificationByCategoryId(@PathVariable("id") Long id);
}
