package com.mall.admin.marketing.vo;

import com.mall.admin.model.PmsProduct;
import com.mall.admin.model.SmsFlashPromotionProductRelation;
import lombok.Getter;
import lombok.Setter;

/**
 * 限时购及商品信息封装
 * Created by macro on 2018/11/16.
 */
public class SmsFlashPromotionProductVO extends SmsFlashPromotionProductRelation {
    @Getter
    @Setter
    private PmsProduct product;
}
