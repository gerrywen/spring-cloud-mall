package com.mall.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenguoli
 * @date 2019/9/19 15:31
 */
@Component
@ConfigurationProperties(prefix = "mall.jwt")
@Data
public class JwtProperties {
    /**
     * JWT存储的请求头
     */
    String tokenHeader;

    /**
     * 秘钥串
     */
    String secret;

    /**
     * 过期时间
     */
    Integer expiration;

    /**
     * JWT负载中拿到开头
     */
    String tokenHead;

    /**
     * 允许忽略校验地址
     */
    List<String> ignoreUri = new ArrayList<>();
}
