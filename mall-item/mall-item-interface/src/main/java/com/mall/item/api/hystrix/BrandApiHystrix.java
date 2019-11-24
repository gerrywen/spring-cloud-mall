package com.mall.item.api.hystrix;

import com.mall.item.api.BrandApi;
import com.mall.item.pojo.Brand;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * program: spring-cloud-mall->BrandApiHystrix
 * description: 熔断器降权
 * author: gerry
 * created: 2019-11-24 23:40
 **/
@Component
public class BrandApiHystrix implements BrandApi {
    @Override
    public List<Brand> queryBrandByIds(List<Long> ids) {
        return null;
    }
}
