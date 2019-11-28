package com.mall.admin.auth.service;


import com.mall.admin.auth.dto.UmsAdminPermissionDTO;
import com.mall.admin.auth.dto.UmsPermissionNode;
import com.mall.admin.model.UmsAdminPermission;

import java.util.List;

/**
 * program: chengjie-ts->UmsPermissionService
 * description: 后台用户权限管理Service
 * author: gerry
 * created: 2019-08-14 16:13
 **/
public interface UmsPermissionService {
    /**
     * 添加权限
     */
    int create(UmsAdminPermissionDTO permissionDTO);

    /**
     * 修改权限
     */
    int update(Long id, UmsAdminPermissionDTO permissionDTO);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<UmsPermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<UmsAdminPermission> list();
}
