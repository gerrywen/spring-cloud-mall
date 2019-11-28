package com.mall.admin.auth.dto;

import com.mall.admin.model.UmsAdminPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * program: chengjie-ts->UmsPermissionNode
 * description: 权限节点
 * author: gerry
 * created: 2019-08-14 16:15
 **/
public class UmsPermissionNode extends UmsAdminPermission {
    @Getter
    @Setter
    private List<UmsPermissionNode> children;
}
