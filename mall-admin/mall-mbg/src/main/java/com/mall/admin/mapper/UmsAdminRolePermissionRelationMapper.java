package com.mall.admin.mapper;

import com.mall.admin.model.UmsAdminRolePermissionRelation;
import com.mall.admin.model.UmsAdminRolePermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminRolePermissionRelationMapper {
    long countByExample(UmsAdminRolePermissionRelationExample example);

    int deleteByExample(UmsAdminRolePermissionRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRolePermissionRelation record);

    int insertSelective(UmsAdminRolePermissionRelation record);

    List<UmsAdminRolePermissionRelation> selectByExample(UmsAdminRolePermissionRelationExample example);

    UmsAdminRolePermissionRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminRolePermissionRelation record, @Param("example") UmsAdminRolePermissionRelationExample example);

    int updateByExample(@Param("record") UmsAdminRolePermissionRelation record, @Param("example") UmsAdminRolePermissionRelationExample example);

    int updateByPrimaryKeySelective(UmsAdminRolePermissionRelation record);

    int updateByPrimaryKey(UmsAdminRolePermissionRelation record);
}