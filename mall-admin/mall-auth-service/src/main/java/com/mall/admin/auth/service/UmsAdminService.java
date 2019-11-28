package com.mall.admin.auth.service;

import com.mall.admin.auth.dto.UmsAdminDTO;
import com.mall.admin.model.UmsAdmin;
import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * program: chengjie-ts->UmsAdminService
 * description: 后台管理员Service
 * author: gerry
 * created: 2019-08-05 16:39
 **/
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     * @param username 管理员名
     * @return 管理员信息
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册
     * @param adminDTO 注册管理员请求参数
     * @return 管理员信息
     */
    UmsAdmin register(UmsAdminDTO adminDTO);

    /**
     * 登录
     * @param username 用户名
     * @param password 用户密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 新token的功能
     * @param oldToken 旧的token
     * @return 生成新的JWT的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     * @param id 管理员ID
     * @return 管理员信息
     */
    UmsAdmin getItem(Long id);


    /**
     * 根据用户名或昵称分页查询用户
     * @param username 用户名
     * @param pageSize 条数
     * @param pageNum 页数
     * @return 管理员列表信息
     */
    List<UmsAdmin> list(String username, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     * @param id 管理员ID
     * @param adminDTO 管理员信息
     * @return 操作结果
     */
    int update(Long id, UmsAdminDTO adminDTO);


    /**
     * 删除指定用户
     * @param id 管理员ID
     * @return 操作结果
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     * @param adminId 管理员ID
     * @param roleIds 角色ids
     * @return 操作结果
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     * @param adminId 管理员ID
     * @return 获取管理员角色列表
     */
    List<UmsAdminRole> getRoleList(Long adminId);

    /**
     * 修改用户的+-权限
     * @param adminId 管理员ID
     * @param permissionIds 权限ids
     * @return 操作结果
     */
    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     * @param adminId 管理员ID
     * @return 获取管理员权限列表
     */
    List<UmsAdminPermission> getPermissionList(Long adminId);
}
