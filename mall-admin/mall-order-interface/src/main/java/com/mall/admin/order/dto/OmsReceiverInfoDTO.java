package com.mall.admin.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单修改收货人信息参数
 * Created by macro on 2018/10/29.
 */
@Data
public class OmsReceiverInfoDTO {
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("收货人名字")
    private String receiverName;

    @ApiModelProperty("收货人手机")
    private String receiverPhone;

    @ApiModelProperty("收货人编码")
    private String receiverPostCode;

    @ApiModelProperty("收货人详细地址")
    private String receiverDetailAddress;

    @ApiModelProperty("收货人省份")
    private String receiverProvince;

    @ApiModelProperty("收货人城市")
    private String receiverCity;

    @ApiModelProperty("收货人地区")
    private String receiverRegion;

    @ApiModelProperty("地址状态")
    private Integer status;
}
