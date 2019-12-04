package com.mall.admin.order.dao;

import com.mall.admin.model.OmsOrder;
import com.mall.admin.order.dto.OmsOrderQueryDTO;
import com.mall.admin.order.vo.OmsOrderDeliveryVO;
import com.mall.admin.order.vo.OmsOrderDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单自定义查询Dao
 * Created by macro on 2018/10/12.
 */
public interface OmsOrderDao {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryDTO queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryVO> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetailVO getDetail(@Param("id") Long id);
}
