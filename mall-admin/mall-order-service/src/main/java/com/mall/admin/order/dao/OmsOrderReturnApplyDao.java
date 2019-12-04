package com.mall.admin.order.dao;

import com.mall.admin.model.OmsOrderReturnApply;
import com.mall.admin.order.dto.OmsReturnApplyQueryDTO;
import com.mall.admin.order.vo.OmsOrderReturnApplyResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请自定义Dao
 * Created by macro on 2018/10/18.
 */
public interface OmsOrderReturnApplyDao {
    /**
     * 查询申请列表
     */
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryDTO queryParam);

    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResultVO getDetail(@Param("id") Long id);
}
