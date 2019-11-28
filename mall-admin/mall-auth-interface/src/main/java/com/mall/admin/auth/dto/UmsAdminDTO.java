package com.mall.admin.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * program: chengjie-ts->UmsAdminParam
 * description: 用户登录参数
 * author: gerry
 * created: 2019-08-05 16:42
 **/
@Data
public class UmsAdminDTO {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "用户头像")
    private String icon;
    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不合法")
    private String email;
    @ApiModelProperty(value = "用户昵称")
//    @JsonProperty("nick_name")
    private String nickName;
    @ApiModelProperty(value = "备注")
    private String note;
}
