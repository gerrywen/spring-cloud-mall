package com.mall.admin.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * program: chengjie-ts->UmsAdminParam
 * description: 用户登录参数
 * author: gerry
 * created: 2019-08-05 16:42
 **/
@Data
@ApiModel(description = "响应数据")
public class UmsAdminInfoVO {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "角色信息")
    private String[] roles;

    @ApiModelProperty(value = "用户头像")
    private String icon;
}
