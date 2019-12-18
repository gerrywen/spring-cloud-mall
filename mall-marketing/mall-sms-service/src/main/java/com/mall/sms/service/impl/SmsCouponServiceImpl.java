package com.mall.sms.service.impl;

import com.mall.admin.mapper.SmsCouponHistoryMapper;
import com.mall.admin.model.SmsCouponHistory;
import com.mall.admin.model.SmsCouponHistoryExample;
import com.mall.sms.service.SmsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author wenguoli
 * @date 2019/12/18 10:27
 */
@Service
public class SmsCouponServiceImpl implements SmsCouponService {

    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;

    @Override
    public void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        if (couponId == null) return;
        //查询第一张优惠券
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        example.createCriteria().andMemberIdEqualTo(memberId)
                .andCouponIdEqualTo(couponId).andUseStatusEqualTo(useStatus == 0 ? 1 : 0);
        List<SmsCouponHistory> couponHistoryList = couponHistoryMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(couponHistoryList)) {
            SmsCouponHistory couponHistory = couponHistoryList.get(0);
            couponHistory.setUseTime(new Date());
            couponHistory.setUseStatus(useStatus);
            couponHistoryMapper.updateByPrimaryKeySelective(couponHistory);
        }
    }
}
