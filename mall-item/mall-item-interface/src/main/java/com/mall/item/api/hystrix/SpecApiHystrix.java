package com.mall.item.api.hystrix;

import com.mall.item.api.SpecApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->SpecApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-14 20:37
 **/
@Component
public class SpecApiHystrix implements SpecApi {
    @Override
    public ResponseEntity<String> querySpecificationByCategoryId(Long id) {
        return null;
    }
}
