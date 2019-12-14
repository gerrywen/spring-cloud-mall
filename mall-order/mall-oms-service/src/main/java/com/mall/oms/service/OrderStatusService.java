package com.mall.oms.service;

import com.mall.oms.dto.CommentsParameterDTO;
import com.mall.oms.dto.OrderStatusMessageDTO;

/**
 * program: spring-cloud-mall->OrderStatusService
 * description: 发送延时消息和评论消息
 * author: gerry
 * created: 2019-12-14 09:25
 **/
public interface OrderStatusService {

    /**
     * 发送消息到延时队列
     * @param orderStatusMessage
     */
    void sendMessage(OrderStatusMessageDTO orderStatusMessage);

    /**
     * 发送评论信息
     * @param commentsParameter
     */
    void sendComments(CommentsParameterDTO commentsParameter);
}
