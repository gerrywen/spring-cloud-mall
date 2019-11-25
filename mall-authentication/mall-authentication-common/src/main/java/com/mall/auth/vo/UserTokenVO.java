package com.mall.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * program: spring-cloud-mall->UserTokenVO
 * description: 用户token返回
 * author: gerry
 * created: 2019-11-25 22:22
 **/
@Data
@ApiModel(description = "响应数据")
public class UserTokenVO {
    @ApiModelProperty(value = "token值")
    private String token;

    @ApiModelProperty(value = "token头部")
    @JsonProperty("token_head")
    private String tokenHead;
}
