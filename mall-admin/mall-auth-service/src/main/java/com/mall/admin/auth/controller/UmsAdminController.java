package com.mall.admin.auth.controller;

import com.mall.admin.auth.dto.UmsAdminDTO;
import com.mall.admin.auth.dto.UmsAdminLoginDTO;
import com.mall.admin.auth.service.UmsAdminService;
import com.mall.admin.auth.vo.UmsAdminInfoVO;
import com.mall.admin.auth.vo.UmsAdminTokenVO;
import com.mall.admin.auth.vo.UmsAdminVO;
import com.mall.admin.base.api.CommonPage;
import com.mall.admin.base.api.CommonResult;
import com.mall.admin.auth.properties.JwtProperties;
import com.mall.admin.model.UmsAdmin;
import com.mall.admin.model.UmsAdminPermission;
import com.mall.admin.model.UmsAdminRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "UmsAdminController", tags = "auth-后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    /**
     * JWT存储的请求头
     */
    private String tokenHeader;

    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;

    /**
     * 构造方法
     *
     * @param jwtProperties jwt配置文件
     */
    public UmsAdminController(JwtProperties jwtProperties) {
        this.tokenHeader = jwtProperties.getTokenHeader();
        this.tokenHead = jwtProperties.getTokenHead();
    }


    /**
     * 用户注册
     *
     * @param umsAdminDTO 请求参数
     * @return umsAdminVO 成功注册的用户信息
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdminVO> register(@RequestBody UmsAdminDTO umsAdminDTO) {
        UmsAdmin umsAdmin = adminService.register(umsAdminDTO);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        UmsAdminVO umsAdminVO = new UmsAdminVO();
        BeanUtils.copyProperties(umsAdmin, umsAdminVO);
        return CommonResult.success(umsAdminVO);
    }

    /**
     * 登录
     *
     * @param umsAdminLoginDTO 登录请求参数
     * @return umsAdminTokenVO 用户token信息
     */
    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult<UmsAdminTokenVO> login(@RequestBody UmsAdminLoginDTO umsAdminLoginDTO) {
        String token = adminService.login(umsAdminLoginDTO.getUsername(), umsAdminLoginDTO.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        UmsAdminTokenVO umsAdminTokenVO = new UmsAdminTokenVO();
        umsAdminTokenVO.setToken(token);
        umsAdminTokenVO.setTokenHead(tokenHead);
        return CommonResult.success(umsAdminTokenVO);
    }

    /**
     * 更新token
     *
     * @param request HttpServletRequest，获取头部信息
     * @return 返回刷新后的token
     */
    @ApiOperation(value = "刷新token")
    @PostMapping("/token/refresh")
    public CommonResult<UmsAdminTokenVO> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed();
        }
        UmsAdminTokenVO umsAdminTokenVO = new UmsAdminTokenVO();
        umsAdminTokenVO.setToken(token);
        umsAdminTokenVO.setTokenHead(tokenHead);
        return CommonResult.success(umsAdminTokenVO);
    }

    /**
     * 获取当前登录用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/info/{username}")
    public CommonResult<UmsAdminInfoVO> getAdminInfo(@PathVariable String username) {
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        UmsAdminInfoVO umsAdminInfoVO = new UmsAdminInfoVO();
        umsAdminInfoVO.setUsername(umsAdmin.getUsername());
        umsAdminInfoVO.setRoles(new String[]{"TEST"});
        umsAdminInfoVO.setIcon(umsAdmin.getIcon());
        return CommonResult.success(umsAdminInfoVO);
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/info")
    public CommonResult<UmsAdminInfoVO> getAdminInfo(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String userName = adminService.getUserNameFromToken(token);
        UmsAdmin umsAdmin = adminService.getAdminByUsername(userName);
        UmsAdminInfoVO umsAdminInfoVO = new UmsAdminInfoVO();
        umsAdminInfoVO.setUsername(umsAdmin.getUsername());
        umsAdminInfoVO.setRoles(new String[]{"TEST"});
        umsAdminInfoVO.setIcon(umsAdmin.getIcon());
        return CommonResult.success(umsAdminInfoVO);
    }

    /**
     * 登出功能
     *
     * @return 成功
     */
    @ApiOperation(value = "登出功能")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    /**
     * 根据用户名或姓名分页获取用户列表
     *
     * @param name     用户名或姓名
     * @param pageSize 页数
     * @param pageNum  当前页
     * @return 管理员列表
     */
    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(name, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    /**
     * 获取指定用户信息
     *
     * @param id 管理员ID
     * @return 管理员信息
     */
    @ApiOperation("获取指定用户信息")
    @GetMapping("/{id}")
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id) {
        UmsAdmin umsAdmin = adminService.getItem(id);
        return CommonResult.success(umsAdmin);
    }


    /**
     * 修改指定用户信息
     *
     * @param id          管理员ID
     * @param umsAdminDTO 管理员信息
     * @return 管理员信息
     */
    @ApiOperation("修改指定用户信息")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdminDTO umsAdminDTO) {

        int count = adminService.update(id, umsAdminDTO);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    /**
     * 删除指定用户信息
     *
     * @param id 管理员ID
     * @return 成功失败结果
     */
    @ApiOperation("删除指定用户信息")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 给用户分配角色
     *
     * @param adminId 管理员ID
     * @param roleIds 角色IDS
     * @return 成功失败结果
     */
    @ApiOperation("给用户分配角色")
    @PostMapping("/role/update")
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 获取指定用户的角色
     *
     * @param adminId 管理员ID
     * @return 成功失败结果
     */
    @ApiOperation("获取指定用户的角色")
    @GetMapping("/role/{adminId}")
    public CommonResult<List<UmsAdminRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsAdminRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }

    /**
     * 给用户分配+-权限
     *
     * @param adminId       管理员ID
     * @param permissionIds 权限IDS
     * @return 成功失败结果
     */
    @ApiOperation("给用户分配+-权限")
    @PostMapping("/permission/update")
    public CommonResult updatePermission(@RequestParam Long adminId,
                                         @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = adminService.updatePermission(adminId, permissionIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    /**
     * 获取用户所有权限（包括+-权限）
     *
     * @param adminId 管理员ID
     * @return 权限列表
     */
    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permission/{adminId}")
    public CommonResult<List<UmsAdminPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsAdminPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
