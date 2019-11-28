package com.mall.admin.auth.dao;

import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminRole;
import com.mall.admin.model.UmsAdminRoleRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * program: chengjie-ts->UmsAdminRoleRelationDao
 * description: 后台用户角色管理自定义Dao
 * author: gerry
 * created: 2019-08-08 16:57
 **/
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<UmsAdminRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    List<UmsAdminPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsAdminPermission> getPermissionList(@Param("adminId") Long adminId);
}
