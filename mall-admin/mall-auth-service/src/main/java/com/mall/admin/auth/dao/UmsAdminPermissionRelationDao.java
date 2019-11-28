package com.mall.admin.auth.dao;

import com.mall.admin.model.UmsAdminPermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * program: chengjie-ts->UmsAdminPermissionRelationDao
 * description: 用户权限自定义Dao
 * author: gerry
 * created: 2019-08-09 16:23
 **/
public interface UmsAdminPermissionRelationDao {
    int insertList(@Param("list") List<UmsAdminPermissionRelation> list);
}
