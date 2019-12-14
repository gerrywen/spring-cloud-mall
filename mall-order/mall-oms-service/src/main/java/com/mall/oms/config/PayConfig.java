package com.mall.oms.config;

import com.github.wxpay.sdk.WXPayConfig;
import com.mall.oms.properties.PayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

/**
 * program: spring-cloud-mall->PayConfig
 * description:
 * author: gerry
 * created: 2019-12-14 08:29
 **/
@Configuration
public class PayConfig implements WXPayConfig {
    @Autowired
    private PayProperties payProperties;

    @Override
    public String getAppID() {
        return payProperties.getAppId();
    }

    @Override
    public String getMchID() {
        return payProperties.getMchId();
    }

    @Override
    public String getKey() {
        return payProperties.getKey();
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return payProperties.getConnectTimeoutMs();
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return payProperties.getReadTimeoutMs();
    }
}
