package com.mall.admin.auth.service;

import com.mall.admin.auth.dto.UmsAdminRoleDTO;
import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * program: chengjie-ts->UmsRoleService
 * description: 后台角色管理Service
 * author: gerry
 * created: 2019-08-14 16:13
 **/
public interface UmsRoleService {
    /**
     * 添加角色
     */
    int create(UmsAdminRoleDTO roleDTO);

    /**
     * 修改角色信息
     */
    int update(Long id, UmsAdminRoleDTO roleDTO);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     */
    List<UmsAdminPermission> getPermissionList(Long roleId);

    /**
     * 修改指定角色的权限
     */
    @Transactional
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取角色列表
     */
    List<UmsAdminRole> list();
}
