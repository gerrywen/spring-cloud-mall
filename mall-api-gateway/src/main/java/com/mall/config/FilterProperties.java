package com.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->FilterProperties
 * description: 服务过滤
 * author: gerry
 * created: 2019-11-25 22:46
 **/
@Component
@ConfigurationProperties(prefix = "mall.filter")
@Data
public class FilterProperties {
    /**
     * 过滤服务
     */
    private String allowPaths;
}
