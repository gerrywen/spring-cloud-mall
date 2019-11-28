package com.mall.admin.auth.controller;

import com.mall.admin.auth.dto.UmsAdminRoleDTO;
import com.mall.admin.auth.service.UmsRoleService;
import com.mall.admin.base.api.CommonResult;
import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * program: chengjie-ts->UmsRoleController
 * description: 后台用户角色管理
 * author: gerry
 * created: 2019-08-14 17:04
 **/
@RestController
@Api(tags = "UmsRoleController", value = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;

    /**
     * 添加用户角色
     * @param roleDTO 请求数据
     * @return 成功失败接口
     */
    @ApiOperation(value = "添加角色")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsAdminRoleDTO roleDTO) {
        int count = roleService.create(roleDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 更新角色信息
     * @param id 角色ID
     * @param roleDTO 请求数据
     * @return 成功失败状态
     */
    @ApiOperation(value = "更新角色")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdminRoleDTO roleDTO) {
        int count = roleService.update(id, roleDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 批量删除角色
     * @param ids 角色ids
     * @return 成功失败状态
     */
    @ApiOperation(value = "批量删除角色")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = roleService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 获取相应角色权限
     * @param roleId 角色ID
     * @return 权限列表
     */
    @ApiOperation(value = "获取相应角色权限")
    @GetMapping("/permission/{roleId}")
    public CommonResult<List<UmsAdminPermission>> getPermissionList(@PathVariable Long roleId) {
        List<UmsAdminPermission> permissionList = roleService.getPermissionList(roleId);
        return CommonResult.success(permissionList);
    }

    /**
     * 修改角色权限
     * @param roleId 角色ID
     * @param permissionIds 权限IDS
     * @return 成功失败状态
     */
    @ApiOperation("修改角色权限")
    @PostMapping("/permission/update")
    public CommonResult updatePermission(@RequestParam Long roleId,
                                         @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = roleService.updatePermission(roleId, permissionIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 获取所有角色
     * @return 角色列表
     */
    @ApiOperation("获取所有角色")
    @GetMapping("/list")
    public Object list() {
        List<UmsAdminRole> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

}
