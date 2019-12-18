package com.mall.item.service;

import com.mall.item.vo.PmsCartProductVO;
import com.mall.item.vo.PmsPromotionProductVO;

import java.util.List;

/**
 * program: spring-cloud-mall->PmsProductService
 * description:
 * author: gerry
 * created: 2019-12-18 22:59
 **/
public interface PmsProductService {
    /**
     * 获取购物车需要的商品信息
     * @param id
     * @return
     */
    PmsCartProductVO getCartProduct(Long id);

    /**
     * 获取促销商品列表
     * @param ids
     * @return
     */
    List<PmsPromotionProductVO> getPromotionProductList(List<Long> ids);
}
