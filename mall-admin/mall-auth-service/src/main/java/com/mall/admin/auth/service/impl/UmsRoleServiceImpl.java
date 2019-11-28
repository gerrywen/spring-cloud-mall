package com.mall.admin.auth.service.impl;

import com.mall.admin.auth.dao.UmsAdminRolePermissionRelationDao;
import com.mall.admin.auth.dto.UmsAdminRoleDTO;
import com.mall.admin.auth.service.UmsRoleService;
import com.mall.admin.mapper.UmsAdminRoleMapper;
import com.mall.admin.mapper.UmsAdminRolePermissionRelationMapper;
import com.mall.admin.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * program: chengjie-ts->UmsRoleServiceImpl
 * description: 后台角色管理Service实现类
 * author: gerry
 * created: 2019-08-14 16:16
 **/
@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    /**
     * 角色管理
     */
    @Autowired
    private UmsAdminRoleMapper roleMapper;

    /**
     * 角色-权限-关联
     */
    @Autowired
    private UmsAdminRolePermissionRelationMapper rolePermissionRelationMapper;

    /**
     * 角色-权限-关联-dao层
     */
    @Autowired
    private UmsAdminRolePermissionRelationDao rolePermissionRelationDao;


    /**
     * 创建数据
     * @param roleDTO 角色数据
     * @return 状态： 1=>成功 0=>失败
     */
    @Override
    public int create(UmsAdminRoleDTO roleDTO) {
        UmsAdminRole adminRole = new UmsAdminRole();
        BeanUtils.copyProperties(roleDTO, adminRole);
        adminRole.setCreateTime(new Date());
        adminRole.setStatus(1);
        adminRole.setAdminCount(0);
        adminRole.setSort(0);
        return roleMapper.insert(adminRole);
    }

    /**
     * 更新角色
     * @param id 主键
     * @param roleDTO 角色数据
     * @return 状态： 1=>成功 0=>失败
     */
    @Override
    public int update(Long id, UmsAdminRoleDTO roleDTO) {
        UmsAdminRole adminRole = new UmsAdminRole();
        BeanUtils.copyProperties(roleDTO, adminRole);
        adminRole.setId(id);
        return roleMapper.updateByPrimaryKeySelective(adminRole);
    }

    /**
     * 删除多个角色
     * @param ids 多个角色ID
     * @return 状态： 1=>成功 0=>失败
     */
    @Override
    public int delete(List<Long> ids) {
        UmsAdminRoleExample example = new UmsAdminRoleExample();
        example.createCriteria().andIdIn(ids);
        return roleMapper.deleteByExample(example);
    }

    /**
     * 根据角色ID获取所有角色的权限
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public List<UmsAdminPermission> getPermissionList(Long roleId) {
        return rolePermissionRelationDao.getPermissionList(roleId);
    }

    /**
     * 更新角色权限关系表
     * @param roleId 角色ID
     * @param permissionIds 权限ids
     * @return 状态： 1=>成功 0=>失败
     */
    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        //先删除原有关系
        UmsAdminRolePermissionRelationExample example = new UmsAdminRolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionRelationMapper.deleteByExample(example);
        //批量插入新关系
        List<UmsAdminRolePermissionRelation> relationList = new ArrayList<>();
        for (Long permissionId: permissionIds) {
            UmsAdminRolePermissionRelation relation = new UmsAdminRolePermissionRelation();
            relation.setRoleId(roleId);
            relation.setPermissionId(permissionId);
            relationList.add(relation);
        }
        return rolePermissionRelationDao.insertList(relationList);
    }

    /**
     * 获取所有的角色
     * @return 角色列表
     */
    @Override
    public List<UmsAdminRole> list() {
        return roleMapper.selectByExample(new UmsAdminRoleExample());
    }
}
