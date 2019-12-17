package com.mall.oms.api.hystrix;

import com.mall.common.base.response.Result;
import com.mall.oms.api.OmsOrderApi;
import com.mall.oms.po.CartPromotionItem;
import com.mall.oms.pojo.Order;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
 * program: spring-cloud-mall->OrderApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-14 08:14
 **/
@Component
public class OmsOrderApiHystrix implements OmsOrderApi {
    @Override
    public Result<List<Long>> createOrder(String seck, @Valid Order order) {
        return null;
    }

    @Override
    public Result<Boolean> updateOrderStatus(Long id, Integer status) {
        return null;
    }

    @Override
    public Result<List<CartPromotionItem>> listPromotion() {
        return null;
    }
}
