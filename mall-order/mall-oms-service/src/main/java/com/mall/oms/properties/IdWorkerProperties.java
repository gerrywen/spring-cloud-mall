package com.mall.oms.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * program: spring-cloud-mall->IdWorkerProperties
 * description:
 * author: gerry
 * created: 2019-12-14 08:32
 **/
@Configuration
@Data
@RefreshScope
public class IdWorkerProperties {
    /**
     * 当前机器id
     */
    @Value("${mall.worker.workerId}")
    private long workerId;

    /**
     * 序列号
     */
    @Value("${mall.worker.dataCenterId}")
    private long dataCenterId;
}
