package com.mall.admin.mapper;

import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminPermissionMapper {
    long countByExample(UmsAdminPermissionExample example);

    int deleteByExample(UmsAdminPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminPermission record);

    int insertSelective(UmsAdminPermission record);

    List<UmsAdminPermission> selectByExample(UmsAdminPermissionExample example);

    UmsAdminPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminPermission record, @Param("example") UmsAdminPermissionExample example);

    int updateByExample(@Param("record") UmsAdminPermission record, @Param("example") UmsAdminPermissionExample example);

    int updateByPrimaryKeySelective(UmsAdminPermission record);

    int updateByPrimaryKey(UmsAdminPermission record);
}