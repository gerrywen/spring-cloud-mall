package com.mall.seckill.service.impl;

import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.common.base.utils.JsonUtils;
import com.mall.common.redis.constant.RedisKey;
import com.mall.common.redis.enums.CtimsModelEnum;
import com.mall.common.redis.utils.CommonRedisUtils;
import com.mall.item.pojo.SeckillGoods;
import com.mall.item.pojo.Stock;
import com.mall.oms.pojo.Order;
import com.mall.oms.pojo.OrderDetail;
import com.mall.oms.pojo.SeckillOrder;
import com.mall.seckill.client.PmsProductFeignClient;
import com.mall.seckill.client.OmsOrderFeignClient;
import com.mall.seckill.mapper.SeckillMapper;
import com.mall.seckill.mapper.SeckillOrderMapper;
import com.mall.seckill.mapper.SkuMapper;
import com.mall.seckill.mapper.StockMapper;
import com.mall.seckill.service.SeckillService;
import com.mall.seckill.po.SeckillMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-11-10 16:57
 * @Feature:
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private PmsProductFeignClient goodsClient;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private OmsOrderFeignClient orderClient;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private CommonRedisUtils redisUtils;


    /**
     * 创建订单
     *
     * @param seckillGoods
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(SeckillGoods seckillGoods) {

        Order order = new Order();
        order.setPaymentType(1);
        order.setTotalPay(seckillGoods.getSeckillPrice());
        order.setActualPay(seckillGoods.getSeckillPrice());
        order.setPostFee(0 + "");
        order.setReceiver("李四");
        order.setReceiverMobile("15812312312");
        order.setReceiverCity("西安");
        order.setReceiverDistrict("碑林区");
        order.setReceiverState("陕西");
        order.setReceiverZip("000000000");
        order.setInvoiceType(0);
        order.setSourceType(2);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setSkuId(seckillGoods.getSkuId());
        orderDetail.setNum(1);
        orderDetail.setTitle(seckillGoods.getTitle());
        orderDetail.setImage(seckillGoods.getImage());
        orderDetail.setPrice(seckillGoods.getSeckillPrice());
        orderDetail.setOwnSpec(this.skuMapper.selectByPrimaryKey(seckillGoods.getSkuId()).getOwnSpec());

        order.setOrderDetails(Arrays.asList(orderDetail));


        String seck = "seckill";

        Result<List<Long>> responseEntity = this.orderClient.createOrder(seck, order);

        if (responseEntity.getCode() == CodeMsg.SUCCESS.getCode()) {
            //库存不足，返回null
            return null;
        }
        return responseEntity.getData().get(0);
    }

    /**
     * 检查秒杀库存
     *
     * @param skuId
     * @return
     */
    @Override
    public boolean queryStock(Long skuId) {
        Stock stock = this.stockMapper.selectByPrimaryKey(skuId);
        if (stock.getSeckillStock() - 1 < 0) {
            return false;
        }
        return true;
    }

    /**
     * 发送消息到秒杀队列当中
     *
     * @param seckillMessage
     */
    @Override
    public void sendMessage(SeckillMessage seckillMessage) {
        String json = JsonUtils.serialize(seckillMessage);
        try {
            this.amqpTemplate.convertAndSend("order.seckill", json);
        } catch (Exception e) {
            LOGGER.error("秒杀商品消息发送异常，商品id：{}", seckillMessage.getSeckillGoods().getSkuId(), e);
        }
    }

    /**
     * 根据用户id查询秒杀订单
     *
     * @param userId
     * @return
     */
    @Override
    public Long checkSeckillOrder(Long userId) {
        Example example = new Example(SeckillOrder.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<SeckillOrder> seckillOrders = this.seckillOrderMapper.selectByExample(example);
        if (seckillOrders == null || seckillOrders.size() == 0) {
            return null;
        }
        return seckillOrders.get(0).getOrderId();
    }

    /**
     * 创建秒杀地址
     *
     * @param goodsId
     * @param id
     * @return
     */
    @Override
    public String createPath(Long goodsId, Long id) {
        String str = new BCryptPasswordEncoder().encode(goodsId.toString() + id);

        String key = id.toString() + "_" + goodsId;
        redisUtils.addMap(CtimsModelEnum.CTIMS_SECKILL_CAP, RedisKey.SECKILL_STRING_PATH, key, str, 60);
        return str;
    }

    /**
     * 验证秒杀地址
     *
     * @param goodsId
     * @param id
     * @param path
     * @return
     */
    @Override
    public boolean checkSeckillPath(Long goodsId, Long id, String path) {
        String key = id.toString() + "_" + goodsId;
        // 获取Redis
        BoundHashOperations<String,Object,Object> hashOperations = redisUtils.getMapField(CtimsModelEnum.CTIMS_SECKILL_CAP, RedisKey.SECKILL_STRING_PATH, key);
        String encodePath = (String) hashOperations.get(key);
        return new BCryptPasswordEncoder().matches(path,encodePath);
    }


}
