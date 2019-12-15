package com.mall.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * program: spring-cloud-mall->JwtProperties
 * description: jwt配置属性
 * author: gerry
 * created: 2019-11-25 20:51
 **/
@Component
@ConfigurationProperties(prefix = "mall.jwt")
@RefreshScope
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

}
