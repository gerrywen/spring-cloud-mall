package com.mall.oms.service;

import com.mall.oms.dto.OmsOrderReturnApplyParamDTO;

/**
 * 订单退货管理Service
 * Created by macro on 2018/10/17.
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyParamDTO returnApply);
}
