package com.mall.admin.marketing.service;

import com.mall.admin.marketing.dto.SmsCouponDTO;
import com.mall.admin.marketing.vo.SmsCouponVO;
import com.mall.admin.model.SmsCoupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 优惠券管理Service
 * Created by macro on 2018/8/28.
 */
public interface SmsCouponService {
    /**
     * 添加优惠券
     */
    @Transactional
    int create(SmsCouponDTO couponParam);

    /**
     * 根据优惠券id删除优惠券
     */
    @Transactional
    int delete(Long id);

    /**
     * 根据优惠券id更新优惠券信息
     */
    @Transactional
    int update(Long id, SmsCouponDTO couponParam);

    /**
     * 分页获取优惠券列表
     */
    List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取优惠券详情
     * @param id 优惠券表id
     */
    SmsCouponVO getItem(Long id);
}
