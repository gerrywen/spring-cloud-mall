package com.mall.oms.api.hystrix;

import com.mall.common.base.response.Result;
import com.mall.oms.api.OmsCartItemApi;
import com.mall.oms.po.CartPromotionItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * program: spring-cloud-mall->OmsCartApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-15 20:47
 **/
@Component
public class OmsCartItemApiHystrix implements OmsCartItemApi {
    @Override
    public Result<List<CartPromotionItem>> listPromotion() {
        return null;
    }
}
