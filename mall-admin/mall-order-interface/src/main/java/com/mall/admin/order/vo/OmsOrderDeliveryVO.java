package com.mall.admin.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单发货参数
 * Created by macro on 2018/10/12.
 */
@Data
public class OmsOrderDeliveryVO {
    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("物流公司")
    private String deliveryCompany;

    @ApiModelProperty("物流单号")
    private String deliverySn;
}
