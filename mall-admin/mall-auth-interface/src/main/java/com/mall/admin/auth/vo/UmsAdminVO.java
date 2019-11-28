package com.mall.admin.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * program: chengjie-ts->UmsAdminParam
 * description: 用户登录参数
 * author: gerry
 * created: 2019-08-05 16:42
 **/
@Data
@ApiModel(description = "响应数据")
public class UmsAdminVO {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户昵称")
//    @JsonProperty("nick_name")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
//    @JsonProperty("create_time")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
//    @JsonProperty("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;
}
