package com.mall.item.api.hystrix;

import com.mall.common.base.pojo.PageResult;
import com.mall.common.base.response.Result;
import com.mall.item.api.SpuApi;
import com.mall.item.bo.SpuBo;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->SpuApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-14 20:38
 **/
@Component
public class SpuApiHystrix implements SpuApi {
    @Override
    public Result<PageResult<SpuBo>> querySpuByPage(Integer page, Integer rows, Boolean desc, Boolean saleable) {
        return null;
    }
}
