package com.mall.admin.mapper;

import com.mall.admin.model.UmsAdminRole;
import com.mall.admin.model.UmsAdminRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminRoleMapper {
    long countByExample(UmsAdminRoleExample example);

    int deleteByExample(UmsAdminRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRole record);

    int insertSelective(UmsAdminRole record);

    List<UmsAdminRole> selectByExample(UmsAdminRoleExample example);

    UmsAdminRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminRole record, @Param("example") UmsAdminRoleExample example);

    int updateByExample(@Param("record") UmsAdminRole record, @Param("example") UmsAdminRoleExample example);

    int updateByPrimaryKeySelective(UmsAdminRole record);

    int updateByPrimaryKey(UmsAdminRole record);
}