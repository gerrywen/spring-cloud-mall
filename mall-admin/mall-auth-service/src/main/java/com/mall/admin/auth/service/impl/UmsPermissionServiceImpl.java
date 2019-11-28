package com.mall.admin.auth.service.impl;

import com.mall.admin.auth.dto.UmsAdminPermissionDTO;
import com.mall.admin.auth.dto.UmsPermissionNode;
import com.mall.admin.auth.service.UmsPermissionService;
import com.mall.admin.mapper.UmsAdminPermissionMapper;
import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminPermissionExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * program: chengjie-ts->UmsPermissionServiceImpl
 * description: 后台用户权限管理Service实现类
 * author: gerry
 * created: 2019-08-14 16:17
 **/
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {

    @Autowired
    private UmsAdminPermissionMapper permissionMapper;

    /**
     * 创建权限
     *
     * @param permissionDTO 权限数据
     * @return 状态： 1=>成功 0=>失败
     */
    @Override
    public int create(UmsAdminPermissionDTO permissionDTO) {
        UmsAdminPermission adminPermission = new UmsAdminPermission();
        BeanUtils.copyProperties(permissionDTO, adminPermission);
        adminPermission.setCreateTime(new Date());
        adminPermission.setStatus(1);
        adminPermission.setSort(0);
        return permissionMapper.insert(adminPermission);
    }

    /**
     * 更新权限
     *
     * @param id         权限ID
     * @param permissionDTO 权限数据
     * @return 状态： 1=>成功 0=>失败
     */
    @Override
    public int update(Long id, UmsAdminPermissionDTO permissionDTO) {
        UmsAdminPermission adminPermission = new UmsAdminPermission();
        BeanUtils.copyProperties(permissionDTO, adminPermission);
        adminPermission.setId(id);
        return permissionMapper.updateByPrimaryKeySelective(adminPermission);
    }

    /**
     * 删除多个权限
     *
     * @param ids 权限ids
     * @return 状态： 1=>成功 0=>失败
     */
    @Override
    public int delete(List<Long> ids) {
        UmsAdminPermissionExample example = new UmsAdminPermissionExample();
        example.createCriteria().andIdIn(ids);
        return permissionMapper.deleteByExample(example);
    }

    /**
     * 返回权限树形数据
     *
     * @return 权限列表
     */
    @Override
    public List<UmsPermissionNode> treeList() {
        List<UmsAdminPermission> permissionList = permissionMapper.selectByExample(new UmsAdminPermissionExample());
        return permissionList.stream()
                .filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission, permissionList)).collect(Collectors.toList());
    }

    /**
     * 获取权限列表
     *
     * @return 权限列表
     */
    @Override
    public List<UmsAdminPermission> list() {
        return permissionMapper.selectByExample(new UmsAdminPermissionExample());
    }

    /**
     * 将权限转换为带有子级的权限对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     *
     * @param permission     权限
     * @param permissionList 权限列表
     * @return 权限节点
     */
    private UmsPermissionNode covert(UmsAdminPermission permission, List<UmsAdminPermission> permissionList) {
        UmsPermissionNode node = new UmsPermissionNode();
        BeanUtils.copyProperties(permission, node);
        List<UmsPermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> covert(subPermission, permissionList))
                .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
