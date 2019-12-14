package com.mall.oms.service.impl;

import com.mall.common.base.utils.JsonUtils;
import com.mall.oms.dto.CommentsParameterDTO;
import com.mall.oms.dto.OrderStatusMessageDTO;
import com.mall.oms.service.OrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * program: spring-cloud-mall->OrderStatusServiceImpl
 * description:
 * author: gerry
 * created: 2019-12-14 09:29
 **/
@Service
public class OrderStatusServiceImpl  implements OrderStatusService {

    /**
     * 日志打印
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    /**
     * 消息队列
     */
    @Autowired
    private AmqpTemplate amqpTemplate;



    /**
     * 发送延时消息到延时队列中
     * @param orderStatusMessage
     */
    @Override
    public void sendMessage(OrderStatusMessageDTO orderStatusMessage) {
        String json = JsonUtils.serialize(orderStatusMessage);
        MessageProperties properties;
        if (orderStatusMessage.getType() == 1){
            // 持久性 non-persistent (1) or persistent (2)
            properties = MessagePropertiesBuilder.newInstance().setExpiration("60000").setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        }else {
            properties = MessagePropertiesBuilder.newInstance().setExpiration("90000").setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        }

        Message message = MessageBuilder.withBody(json.getBytes()).andProperties(properties).build();
        //发送消息
        try {
            this.amqpTemplate.convertAndSend("", "mall.order.delay.queue", message);
        }catch (Exception e){
            LOGGER.error("延时消息发送异常，订单号为：id：{}，用户id为：{}",orderStatusMessage.getOrderId(),orderStatusMessage.getUserId(),e);
        }
    }

    /**
     * 将评论发送到消息队列中
     * @param commentsParameter
     */
    @Override
    public void sendComments(CommentsParameterDTO commentsParameter) {
        String json = JsonUtils.serialize(commentsParameter);
        try {
            this.amqpTemplate.convertAndSend("mall.comments.exchange","user.comments", json);
        }catch (Exception e){
            LOGGER.error("评论消息发送异常，订单id：{}",commentsParameter.getOrderId(),e);
        }
    }
}
