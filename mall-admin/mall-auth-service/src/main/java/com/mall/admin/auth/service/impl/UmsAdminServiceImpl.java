package com.mall.admin.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.mall.admin.auth.dao.UmsAdminPermissionRelationDao;
import com.mall.admin.auth.dao.UmsAdminRoleRelationDao;
import com.mall.admin.auth.dto.UmsAdminDTO;
import com.mall.admin.auth.properties.JwtProperties;
import com.mall.admin.auth.service.UmsAdminService;
import com.mall.admin.base.utils.JwtTokenUtils;
import com.mall.admin.mapper.UmsAdminLoginLogMapper;
import com.mall.admin.mapper.UmsAdminMapper;
import com.mall.admin.mapper.UmsAdminPermissionRelationMapper;
import com.mall.admin.mapper.UmsAdminRoleRelationMapper;
import com.mall.admin.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * program: chengjie-ts->UmsAdminServiceImpl
 * description: UmsAdminService实现类
 * author: gerry
 * created: 2019-08-05 17:48
 **/
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;

    /**
     * 身份认证
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * jwt加密
     */
    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    /**
     * 密码加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 管理员
     */
    @Autowired
    private UmsAdminMapper adminMapper;

    /**
     * 管理员日志
     */
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;

    /**
     * 管理员关系角色
     */
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    /**
     * 管理员关系权限
     */
    @Autowired
    private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;

    /**
     * 管理员角色权限
     */
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    /**
     * 管理员权限
     */
    @Autowired
    private UmsAdminPermissionRelationDao adminPermissionRelationDao;


    /**
     * 构造方法
     *
     * @param jwtProperties jwt配置文件
     */
    public UmsAdminServiceImpl(JwtProperties jwtProperties) {
        this.tokenHead = jwtProperties.getTokenHead();
    }

    /**
     * 获取管理员信息
     *
     * @param username 管理员名
     * @return 管理员信息
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    /**
     * 注册
     *
     * @param adminDTO 注册管理员请求参数
     * @return 管理员信息
     */
    @Override
    public UmsAdmin register(UmsAdminDTO adminDTO) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(adminDTO, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }

        return token;
    }

    /**
     * 添加登录记录
     *
     * @param username 管理员名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     *
     * @param username 管理员名
     */
    private void updateLoginTimeByUsername(String username) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setLoginTime(new Date());
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(umsAdmin, adminExample);
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public String getUserNameFromToken(String token) {
        token = token.substring(tokenHead.length());
        String username = jwtTokenUtil.getUserNameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(token, userDetails)) {
            return username;
        }
        return null;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String username, Integer pageSize, Integer pageNum) {
        // 设置分页
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample adminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = adminExample.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            String name = "%" + username + "%";
            criteria.andUsernameLike(name);
            // 别名查询
            adminExample.or(criteria.andNickNameLike(name));
        }
        return adminMapper.selectByExample(adminExample);
    }

    @Override
    public int update(Long id, UmsAdminDTO adminDTO) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(adminDTO, umsAdmin);
        umsAdmin.setId(id);
        //密码已经加密处理，需要单独修改
        umsAdmin.setPassword(null);
        return adminMapper.updateByPrimaryKeySelective(umsAdmin);
    }

    @Override
    public int delete(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        //建立新关系
        // 判断角色ids是否为空
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            // 批量添加
            adminRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public List<UmsAdminRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        //删除原所有权限关系
        UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
        relationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminPermissionRelationMapper.deleteByExample(relationExample);
        //获取用户所有角色权限
        List<UmsAdminPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        // java8特性 https://www.cnblogs.com/ngy0217/p/11080840.html
        List<Long> rolePermissionList = permissionList.stream().map(UmsAdminPermission::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<UmsAdminPermissionRelation> relationList = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(adminId, 1, addPermissionIdList));
            relationList.addAll(convert(adminId, -1, subPermissionIdList));
            return adminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<UmsAdminPermissionRelation> convert(Long adminId, Integer type, List<Long> permissionIdList) {
        return permissionIdList.stream().map(permissionId -> {
            UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UmsAdminPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }
}
