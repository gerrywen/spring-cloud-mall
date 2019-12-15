package com.mall.user.mapper;

import com.mall.user.vo.SmsCouponHistoryDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * program: spring-cloud-mall->SmsCouponHistoryMapper
 * description: 会员优惠券领取历史
 * author: gerry
 * created: 2019-12-15 09:35
 **/
public interface CouponHistoryMapper {
    /**
     * 获取历史优惠券领取详情列表
     * @param memberId
     * @return
     */
    List<SmsCouponHistoryDetailVO> getDetailList(@Param("memberId") Long memberId);
}
