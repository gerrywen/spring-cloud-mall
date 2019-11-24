package com.mall.item.bo;

import lombok.Data;

/**
 * program: spring-cloud-mall->SeckillParameter
 * description: 秒杀设置参数
 * author: gerry
 * created: 2019-11-24 23:04
 **/
@Data
public class SeckillParameter {

    /**
     * 要秒杀的sku id
     */
    private Long id;

    /**
     * 秒杀开始时间
     */
    private String startTime;

    /**
     * 秒杀结束时间
     */
    private String endTime;

    /**
     * 参与秒杀的商品数量
     */
    private Integer count;

    /**
     * 折扣
     */
    private double  discount;

}
