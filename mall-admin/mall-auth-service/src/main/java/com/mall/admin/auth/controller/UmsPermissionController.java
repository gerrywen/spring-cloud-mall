package com.mall.admin.auth.controller;

import com.mall.admin.auth.dto.UmsAdminPermissionDTO;
import com.mall.admin.auth.dto.UmsPermissionNode;
import com.mall.admin.auth.service.UmsPermissionService;
import com.mall.admin.base.api.CommonResult;
import com.mall.admin.model.UmsAdminPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * program: chengjie-ts->UmsPermissionController
 * description: 后台用户权限管理
 * author: gerry
 * created: 2019-08-14 17:04
 **/
@RestController
@Api(value = "UmsPermissionController",tags = "auth-后台用户权限管理")
@RequestMapping("/permission")
public class UmsPermissionController {
    @Autowired
    private UmsPermissionService permissionService;

    /**
     * 添加权限
     * @param permissionDTO 请求数据
     * @return 成功失败状态
     */
    @ApiOperation(value = "添加权限")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsAdminPermissionDTO permissionDTO)
    {
        int count = permissionService.create(permissionDTO);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 更新权限
     * @param id 权限ID
     * @param permissionDTO 请求参数
     * @return 成功失败状态
     */
    @ApiOperation(value = "更新权限")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id , @RequestBody UmsAdminPermissionDTO permissionDTO)
    {
        int count = permissionService.update(id, permissionDTO);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 删除权限
     * @param ids 权限IDS
     * @return 成功失败状态
     */
    @ApiOperation("根据id批量删除权限")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids)
    {
        int count = permissionService.delete(ids);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 层级结构的所有权限
     * @return 所有权限
     */
    @ApiOperation("以层级结构返回所有权限")
    @GetMapping("/treeList")
    public CommonResult<List<UmsPermissionNode>> treeList() {
        List<UmsPermissionNode> permissionNodeList = permissionService.treeList();
        return CommonResult.success(permissionNodeList);
    }

    /**
     * 获取所有权限列表
     * @return 所有权限列表
     */
    @ApiOperation("获取所有权限列表")
    @GetMapping("/list")
    public CommonResult<List<UmsAdminPermission>> list() {
        List<UmsAdminPermission> permissionList = permissionService.list();
        return CommonResult.success(permissionList);
    }
}
