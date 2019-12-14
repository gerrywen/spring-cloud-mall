package com.mall.oms.utils;

import com.github.wxpay.sdk.WXPay;
import com.mall.common.redis.constant.RedisKey;
import com.mall.common.redis.enums.CtimsModelEnum;
import com.mall.common.redis.utils.CommonRedisUtils;
import com.mall.oms.config.PayConfig;
import com.mall.oms.properties.UnifiedProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * program: spring-cloud-mall->PayHelper
 * description:
 * author: gerry
 * created: 2019-12-14 08:19
 **/
@Component
public class PayHelper {
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(PayHelper.class);

    /**
     * 微信支付
     */
    private WXPay wxPay;

    /**
     * 统一下单参数
     */
    @Autowired
    private UnifiedProperties unifiedProperties;

    /**
     * redis工具类
     */
    private CommonRedisUtils redisUtils;

    /**
     * 构造
     *
     * @param payConfig
     */
    public PayHelper(PayConfig payConfig) {
        // 真实开发时
        wxPay = new WXPay(payConfig);
        // 测试时
        // wxPay = new WXPay(payConfig, WXPayConstants.SignType.MD5, true);
    }

    /**
     * 统一下单
     *
     * @param orderId
     * @return
     */
    public String createPayUrl(Long orderId) {
        // 存储数据
        String key = RedisKey.ORDER_STRING_PAY_URL;
        try {
            // 获取数据
            String url = redisUtils.get(CtimsModelEnum.CTIMS_ORDER_CAP, key);
            if (StringUtils.isNotBlank(url)) {
                return url;
            }
        } catch (Exception e) {
            logger.error("查询缓存付款链接异常,订单编号：{}", orderId, e);
        }

        try {
            Map<String, String> data = new HashMap<>();
            // 商品描述
            data.put("body", "mall商城测试");
            // 订单号
            data.put("out_trade_no", orderId.toString());
            //货币
            data.put("fee_type", unifiedProperties.getFeeType());
            //金额，单位是分
            data.put("total_fee", "1");
            //调用微信支付的终端IP（estore商城的IP）
            data.put("spbill_create_ip", unifiedProperties.getSpbillCreateIp());
            //回调地址
            data.put("notify_url", unifiedProperties.getNotifyUrl());
            // 交易类型为扫码支付
            data.put("trade_type", unifiedProperties.getTradeType());
            //商品id,使用假数据
            data.put("product_id", "1234567");

            Map<String, String> result = this.wxPay.unifiedOrder(data);

            if ("SUCCESS".equals(result.get("return_code"))) {
                String url = result.get("code_url");
                // 将付款地址缓存，时间为10分钟
                try {
                    redisUtils.set(CtimsModelEnum.CTIMS_ORDER_CAP, key, url, 60 * 10);
                } catch (Exception e) {
                    logger.error("缓存付款链接异常,订单编号：{}", orderId, e);
                }
                return url;
            } else {
                logger.error("创建预交易订单失败，错误信息：{}", result.get("return_msg"));
                return null;
            }
        } catch (Exception e) {
            logger.error("创建预交易订单异常", e);
            return null;
        }
    }

    /**
     * 查询订单状态
     *
     * @param orderId
     * @return
     */
    public PayState queryOrder(Long orderId) {
        Map<String, String> data = new HashMap<>();
        // 订单号
        data.put("out_trade_no", orderId.toString());
        try {
            Map<String, String> result = this.wxPay.orderQuery(data);
            if (result == null) {
                // 未查询到结果，认为是未付款
                return PayState.NOT_PAY;
            }
            String state = result.get("trade_state");
            if ("SUCCESS".equals(state)) {
                // success，则认为付款成功

                // 修改订单状态
//                this.orderService.updateOrderStatus(orderId, 2);
                return PayState.SUCCESS;
            } else if (StringUtils.equals("USERPAYING", state) || StringUtils.equals("NOTPAY", state)) {
                // 未付款或正在付款，都认为是未付款
                return PayState.NOT_PAY;
            } else {
                // 其它状态认为是付款失败
                return PayState.FAIL;
            }
        } catch (Exception e) {
            logger.error("查询订单状态异常", e);
            return PayState.NOT_PAY;
        }
    }

}
