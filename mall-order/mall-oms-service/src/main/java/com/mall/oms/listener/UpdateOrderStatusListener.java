package com.mall.oms.listener;

import com.mall.common.base.utils.JsonUtils;
import com.mall.oms.dto.CommentsParameterDTO;
import com.mall.oms.dto.OrderStatusMessageDTO;
import com.mall.oms.mapper.OrderStatusMapper;
import com.mall.oms.pojo.OrderStatus;
import com.mall.oms.pojo.Review;
import com.mall.oms.service.OrderService;
import com.mall.oms.service.OrderStatusService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * program: spring-cloud-mall->UpdateOrderStatusListener
 * description: 自动修改订单状态：自动确认收货，自动评价
 * author: gerry
 * created: 2019-12-14 09:24
 **/
@Component
public class UpdateOrderStatusListener {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderStatusMapper orderStatusMapper;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "mall.order.delay.ttl.queue",durable = "true"), //队列持久化
            exchange = @Exchange(
                    value = "mall.amq.direct",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"mall_ttl_orderStatus"}
    ))
    public void listenOrderDelayMessage(byte[] str){
        OrderStatusMessageDTO orderStatusMessage = JsonUtils.parse(new String(str), OrderStatusMessageDTO.class);
        if (orderStatusMessage == null){
            return;
        }
        int type = orderStatusMessage.getType();

        if (type == 1){
            //自动确认收货，时间为7天

            //1.查询当前订单状态
            int status = orderService.queryOrderStatusById(orderStatusMessage.getOrderId()).getStatus();
            int nowStatus = 4;
            if (status + 1 == nowStatus){
                //2.修改订单状态
                updateOrderStatusDelay(orderStatusMessage.getOrderId(), nowStatus);

            }
        }else {
            //自动好评，时间为5天
            //1.查询当前订单状态
            int status = orderService.queryOrderStatusById(orderStatusMessage.getOrderId()).getStatus();
            int nowStatus = 6;
            if (status + 2 != nowStatus){
                return;
            }
            //2.修改订单状态
            updateOrderStatusDelay(orderStatusMessage.getOrderId(), nowStatus);
            //3.发送评论消息
            CommentsParameterDTO commentsParameter = constructMessage(orderStatusMessage);

            this.orderStatusService.sendComments(commentsParameter);
        }
    }

    private CommentsParameterDTO constructMessage(OrderStatusMessageDTO orderStatusMessage) {
        Long spuId = orderStatusMessage.getSpuId();
        String content = "默认好评";
        Long userId = orderStatusMessage.getUserId();
        String nickname = orderStatusMessage.getUsername();
        List<String> images = new ArrayList<>();
        boolean iscomment = true;
        String parentId = 0 + "";
        boolean isparent = true;
        int commentType = 1;
        Review review = new Review(orderStatusMessage.getOrderId()+"",spuId+"", content, userId+"", nickname, images, iscomment, parentId,isparent,commentType);
        CommentsParameterDTO commentsParameter = new CommentsParameterDTO();
        commentsParameter.setOrderId(orderStatusMessage.getOrderId());
        commentsParameter.setReview(review);
        return commentsParameter;
    }


    private void updateOrderStatusDelay(Long orderId, int nowStatus) {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setStatus(nowStatus);
        if (nowStatus == 4){
            orderStatus.setEndTime(new Date());
        }
        if (nowStatus == 6){
            orderStatus.setCommentTime(new Date());
        }

        this.orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

}
