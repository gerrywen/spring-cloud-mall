package com.mall.admin.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * program: chengjie-ts->UmsAdminLoginParam
 * description: 用户登录参数
 * author: gerry
 * created: 2019-08-09 16:45
 **/
@Data
public class UmsAdminLoginDTO {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
}
