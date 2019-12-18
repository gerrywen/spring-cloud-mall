package com.mall.sms.service;

/**
 * @author wenguoli
 * @date 2019/12/18 10:27
 */
public interface SmsCouponService {
    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId  优惠券id
     * @param memberId  会员id
     * @param useStatus 0->未使用；1->已使用
     */
    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);
}
