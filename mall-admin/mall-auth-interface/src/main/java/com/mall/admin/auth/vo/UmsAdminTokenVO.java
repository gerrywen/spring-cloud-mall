package com.mall.admin.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "响应数据")
public class UmsAdminTokenVO {
    @ApiModelProperty(value = "token值")
    private String token;

    @ApiModelProperty(value = "token头部")
    private String tokenHead;

}
