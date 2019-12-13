package com.mall.item.vo;

import com.mall.admin.model.PmsProduct;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 秒杀信息和商品对象封装
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
public class FlashPromotionProductVO extends PmsProduct {
    /**
     * 促销价
     */
    private BigDecimal flashPromotionPrice;
    /**
     * 促销数量
     */
    private Integer flashPromotionCount;
    /**
     * 促销限制
     */
    private Integer flashPromotionLimit;
}
