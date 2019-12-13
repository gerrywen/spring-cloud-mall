package com.mall.item.vo;

import com.mall.admin.model.CmsSubject;
import com.mall.admin.model.PmsBrand;
import com.mall.admin.model.PmsProduct;
import com.mall.admin.model.SmsHomeAdvertise;
import lombok.Data;

import java.util.List;

/**
 * program: spring-cloud-mall->HomeContentResultVO
 * description:
 * author: gerry
 * created: 2019-12-13 21:26
 **/
@Data
public class HomeContentResultVO {
    /**
     * 轮播广告
     */
    private List<SmsHomeAdvertise> advertiseList;
    /**
     * 推荐品牌
     */
    private List<PmsBrand> brandList;
    /**
     * 当前秒杀场次
     */
    private HomeFlashPromotionVO homeFlashPromotion;
    /**
     * 新品推荐
     */
    private List<PmsProduct> newProductList;
    /**
     * 人气推荐
     */
    private List<PmsProduct> hotProductList;
    /**
     * 推荐专题
     */
    private List<CmsSubject> subjectList;

}
