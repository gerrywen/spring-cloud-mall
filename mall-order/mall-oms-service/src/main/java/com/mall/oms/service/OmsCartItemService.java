package com.mall.oms.service;

import com.mall.admin.model.OmsCartItem;
import com.mall.oms.po.CartPromotionItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * program: spring-cloud-mall->OmsCartItemService
 * description: 购物车管理Service
 * author: gerry
 * created: 2019-12-15 20:22
 **/
public interface OmsCartItemService {
    /**
     * 查询购物车中是否包含该商品，有增加数量，无添加到购物车
     */
    @Transactional
    int add(OmsCartItem cartItem);

    /**
     * 根据会员编号获取购物车列表
     */
    List<OmsCartItem> list(Long memberId);


    /**
     * 获取包含促销活动信息的购物车列表
     */
    List<CartPromotionItem> listPromotion(Long memberId);

}
