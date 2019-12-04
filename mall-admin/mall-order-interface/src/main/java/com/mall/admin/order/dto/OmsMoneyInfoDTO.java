package com.mall.admin.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 修改订单费用信息参数
 * Created by macro on 2018/10/29.
 */
@Data
public class OmsMoneyInfoDTO {

    @ApiModelProperty("订单ID")
    private Long orderId;

    private BigDecimal freightAmount;

    private BigDecimal discountAmount;

    private Integer status;
}
