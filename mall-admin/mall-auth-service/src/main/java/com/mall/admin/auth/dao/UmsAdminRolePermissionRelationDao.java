package com.mall.admin.auth.dao;

import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminRolePermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * program: chengjie-ts->UmsAdminRolePermissionRelationDao
 * description: 后台用户角色管理自定义Dao
 * author: gerry
 * created: 2019-08-14 16:20
 **/
public interface UmsAdminRolePermissionRelationDao {
    /**
     * 批量插入角色和权限关系
     */
    int insertList(@Param("list") List<UmsAdminRolePermissionRelation> list);

    /**
     * 根据角色获取权限
     */
    List<UmsAdminPermission> getPermissionList(@Param("roleId") Long roleId);
}
