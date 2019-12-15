package com.mall.user.service;

import com.mall.admin.model.SmsCouponHistory;
import com.mall.common.base.response.Result;
import com.mall.oms.po.CartPromotionItem;
import com.mall.user.vo.SmsCouponHistoryDetailVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户优惠券管理Service
 * Created by macro on 2018/8/29.
 */
public interface UmsMemberCouponService {
    /**
     * 会员添加优惠券
     */
    @Transactional
    Result add(Long couponId);

    /**
     * 获取优惠券列表
     * @param useStatus 优惠券的使用状态
     */
    List<SmsCouponHistory> list(Integer useStatus);

    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetailVO> listCart(List<CartPromotionItem> cartItemList, Integer type);
}
