package com.mall.admin.order.service;

import com.mall.admin.model.OmsOrderReturnApply;
import com.mall.admin.order.dto.OmsReturnApplyQueryDTO;
import com.mall.admin.order.dto.OmsUpdateStatusDTO;
import com.mall.admin.order.vo.OmsOrderReturnApplyResultVO;

import java.util.List;

/**
 * 退货申请管理Service
 * Created by macro on 2018/10/18.
 */
public interface OmsOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryDTO queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusDTO statusParam);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResultVO getItem(Long id);
}
