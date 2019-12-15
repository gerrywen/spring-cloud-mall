package com.mall.user.service;

import com.mall.admin.model.UmsMember;
import com.mall.common.base.response.Result;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface UmsMemberService {

    /**
     * 授权登录返回token
     * @param username
     * @param password
     * @return
     */
    public String authentication(String username, String password);

    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    UmsMember queryUser(String username, String password);


    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 用户注册
     */
    @Transactional
    Result register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    Result generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    Result updatePassword(String telephone, String password, String authCode);


    /**
     * 根据用户名修改密码
     * @param username
     * @param newPassword
     * @return
     */
    boolean updateUsernamePassword(String username,String oldPassword,String newPassword);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id, Integer integration);
}
