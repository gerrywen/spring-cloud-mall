package com.mall.oms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->UnifiedProperties
 * description:
 * author: gerry
 * created: 2019-12-14 09:10
 **/
@Component
@ConfigurationProperties(prefix = "mall.unified")
@Data
@RefreshScope
public class UnifiedProperties {
    /**
     * 货币,单位是分 CNY
     */
    private String feeType;

    /**
     * 调用微信支付的终端IP（mall商城的IP）
     */
    private String spbillCreateIp;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 交易类型为扫码支付 NATIVE
     */
    private String tradeType;

}
