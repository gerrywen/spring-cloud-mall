package com.mall.admin.order.service;

import com.mall.admin.model.OmsOrder;
import com.mall.admin.order.dto.OmsMoneyInfoDTO;
import com.mall.admin.order.dto.OmsOrderQueryDTO;
import com.mall.admin.order.dto.OmsReceiverInfoDTO;
import com.mall.admin.order.vo.OmsOrderDeliveryVO;
import com.mall.admin.order.vo.OmsOrderDetailVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单管理Service
 * Created by macro on 2018/10/11.
 */
public interface OmsOrderService {
    /**
     * 订单查询
     */
    List<OmsOrder> list(OmsOrderQueryDTO queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量发货
     */
    @Transactional
    int delivery(List<OmsOrderDeliveryVO> deliveryParamList);

    /**
     * 批量关闭订单
     */
    @Transactional
    int close(List<Long> ids, String note);

    /**
     * 批量删除订单
     */
    int delete(List<Long> ids);

    /**
     * 获取指定订单详情
     */
    OmsOrderDetailVO detail(Long id);

    /**
     * 修改订单收货人信息
     */
    @Transactional
    int updateReceiverInfo(OmsReceiverInfoDTO receiverInfoParam);

    /**
     * 修改订单费用信息
     */
    @Transactional
    int updateMoneyInfo(OmsMoneyInfoDTO moneyInfoParam);

    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);
}
