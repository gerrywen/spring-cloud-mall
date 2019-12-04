package com.mall.admin.oss.config;

import com.aliyun.oss.OSSClient;
import com.mall.admin.oss.properties.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by macro on 2018/5/17.
 */
@Configuration
public class OssConfig {
    @Autowired
    private OssProperties ossProperties;

    @Bean
    public OSSClient ossClient(){
        return new OSSClient(ossProperties.getEndpoint(),ossProperties.getAccessKeyId(),ossProperties.getAccessKeySecret());
    }
}
