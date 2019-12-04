package com.mall.admin.oss.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->OssProperties
 * description: 阿里云oss配置属性
 * author: gerry
 * created: 2019-12-05 00:18
 **/
@Component
@ConfigurationProperties(prefix = "aliyun")
@Data
public class OssProperties {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.policy.expire}")
    private int expire;

    @Value("${aliyun.oss.maxSize}")
    private int maxSize;

    @Value("${aliyun.oss.callback}")
    private String callback;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.dir.prefix}")
    private String prefix;

}
